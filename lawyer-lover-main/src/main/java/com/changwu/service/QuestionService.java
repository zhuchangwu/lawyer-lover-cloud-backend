package com.changwu.service;

import com.changwu.doc.Question;
import com.changwu.exception.ExceptionEnum;
import com.changwu.exception.MyException;
import com.changwu.repository.QuestionRepository;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Author: Changwu
 * @Date: 2019/9/20 18:49
 */
@Service
public class QuestionService {

    @Autowired
    QuestionRepository repository;

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

   /**
    * 根据问题name,返回和当前问题名相似的10个问题名
    *
    * @param preName
    * @return
    */
    public List<String> findQuestionListLikeThis(String preName) {

        CompletionSuggestionBuilder suggestionBuilder = SuggestBuilders.completionSuggestion("questionName.suggest")
                .prefix(preName, Fuzziness.AUTO).size(10);

        SuggestBuilder suggestBuilder = new SuggestBuilder();
        suggestBuilder.addSuggestion("suggesttions", suggestionBuilder);

        SearchResponse suggestResposne = elasticsearchTemplate.suggest(suggestBuilder, Question.class);
        // 获取出建议封装对象
        Suggest suggest = suggestResposne.getSuggest();
        HashSet suggestSet = new HashSet();
        int maxSuggest = 0;
        if (suggest != null) {
            Suggest.Suggestion result = suggest.getSuggestion("suggesttions");//获取suggest,name任意string
            for (Object term : result.getEntries()) {
                if (term instanceof CompletionSuggestion.Entry) {
                    CompletionSuggestion.Entry item = (CompletionSuggestion.Entry) term;
                    if (!item.getOptions().isEmpty()) {
                        //若item的option不为空,循环遍历
                        for (CompletionSuggestion.Entry.Option option : item.getOptions()) {
                            String tip = option.getText().toString();
                            if (!suggestSet.contains(tip)) {
                                suggestSet.add(tip);
                                ++maxSuggest;
                            }
                        }
                    }
                }
                if (maxSuggest >= 5) {
                    break;
                }
            }
        }
        return new ArrayList<>(suggestSet);
    }

    /**
     * 根据问题名称精确拼配, 若精确匹配没结果则全文检索
     *
     * @return
     */
    public List<Question> findOneByQuestionName(String questionName) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.should(QueryBuilders.matchPhraseQuery("questionName", questionName))
                .must(QueryBuilders.matchQuery("questionName", questionName));
        queryBuilder.withQuery(boolQueryBuilder);
        queryBuilder.withPageable(PageRequest.of(0, 5));
        Page<Question> search = repository.search(queryBuilder.build());
        List<Question> content = search.getContent();
        if (content.size() == 0)
            throw new MyException(ExceptionEnum.CAN_NOT_FIND_ANY_ANSWER);
        return content;
    }


    public List<Question> findOneByQuestionName1(String questionName) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        return null;
    }

}

