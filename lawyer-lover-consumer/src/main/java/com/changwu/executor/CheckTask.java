package com.changwu.executor;

import com.changwu.dto.CheckDeferredTaskDto;
import com.changwu.entity.CaseDoc;
import com.changwu.repository.CaseDocRepository;
import com.changwu.utils.JsonUtils;
import com.changwu.utils.ResultVOUtil;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.context.request.async.DeferredResult;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Changwu
 * @Date: 2019/11/7 21:19
 */
public class CheckTask implements Runnable {

    private CheckDeferredTaskDto checkDeferredTaskDto;

    public CheckTask(CheckDeferredTaskDto checkDeferredTaskDto) {
        this.checkDeferredTaskDto=checkDeferredTaskDto;
    }

    @Override
    public void run() {
        MySimHash mySimHash = new MySimHash(checkDeferredTaskDto.getIntroduce());
        // 生成指纹
        List<BigInteger> simHash = mySimHash.getStrSimHash();
        DeferredResult<String> deferredResult = checkDeferredTaskDto.getDeferredResult();
        CaseDocRepository repository  = checkDeferredTaskDto.getRepository();
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        BoolQueryBuilder one = QueryBuilders.boolQuery();

        for (BigInteger hash : simHash) {
            one.should(QueryBuilders.multiMatchQuery(Integer.valueOf(hash.toString()),"one","two","three","four"));
        }
        queryBuilder.withQuery(one);

        Page<CaseDoc> search = repository.search(queryBuilder.build());
        if (search==null){
            deferredResult.setResult(JsonUtils.objectToJson(ResultVOUtil.error(500,"数据库正在更新,未找到任何相似的")));
        }
        List<CaseDoc> content = search.getContent();
        // 如果为空,给前端友好的提示
        if (content!=null&&content.size()==0){
            ArrayList<CaseDoc> list = new ArrayList<>();
            list.add(new CaseDoc("对不起, 未陪配到相似度数据"));
            deferredResult.setResult(JsonUtils.objectToJson(ResultVOUtil.error(500,list)));
            return;
        }
        // 将值填充进 deferredResult
        deferredResult.setResult(JsonUtils.objectToJson(ResultVOUtil.success(content)));
    }
}
