package com.es;

import com.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class esIndexDocInsertBatch {
    public static void main(String[] args) throws IOException {

        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        //批量添加数据
        BulkRequest bulkRequest = new BulkRequest();


        /*bulkRequest.add(new IndexRequest().index("user").id("1001").source(XContentType.JSON, "name", "张三", "age", 18, "sex", "男"));
        bulkRequest.add(new IndexRequest().index("user").id("1002").source(XContentType.JSON, "name", "李四", "age", 19, "sex", "男"));
        bulkRequest.add(new IndexRequest().index("user").id("1004").source(XContentType.JSON, "name", "小明", "age", 20, "sex", "男"));
        bulkRequest.add(new IndexRequest().index("user").id("1005").source(XContentType.JSON, "name", "小舞", "age", 21, "sex", "女"));*/
        bulkRequest.add(new IndexRequest().index("user").id("1006").source(XContentType.JSON, "name", "王五", "age", 23, "sex", "男"));
        bulkRequest.add(new IndexRequest().index("user").id("1007").source(XContentType.JSON, "name", "张三1", "age", 24, "sex", "男"));
        bulkRequest.add(new IndexRequest().index("user").id("1008").source(XContentType.JSON, "name", "张三2", "age", 25, "sex", "男"));
        bulkRequest.add(new IndexRequest().index("user").id("1009").source(XContentType.JSON, "name", "张三11", "age", 26, "sex", "男"));

        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);

        System.out.println(bulkResponse.getTook());
        System.out.println(bulkResponse.getItems());
        restHighLevelClient.close();
    }
}
