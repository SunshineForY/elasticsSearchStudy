package com.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

public class esIndexDocSomeQuery {


    //模糊查询
    public void fuzzQuery() throws IOException {

        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        //查询索引中全部的数据
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("user");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.query(QueryBuilders.fuzzyQuery("name", "三").fuzziness(Fuzziness.ONE));

        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println(searchResponse.getTook());
        for (SearchHit hit : searchResponse.getHits()) {
            System.out.println(hit.getSourceAsString());
        }
        restHighLevelClient.close();

    }


    public void someQuery() throws IOException {
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        //查询索引中全部的数据
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("user");


        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        /*多条件查询  must:等于； mustNot:不等于; should:或者
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //boolQueryBuilder.must(QueryBuilders.matchQuery("sex", "男"));
        //boolQueryBuilder.mustNot(QueryBuilders.matchQuery("age", "18"));
        boolQueryBuilder.should(QueryBuilders.matchQuery("age", 18));
        boolQueryBuilder.should(QueryBuilders.matchQuery("age", 19));
        searchSourceBuilder.query(boolQueryBuilder);*/

        //范围查询
        // gte >= ;
        // lte <= ；
        // gt  >  ;
        // lt  <  ;
        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("age");

        rangeQuery.gte("18");
        rangeQuery.lt("20");

        searchSourceBuilder.query(rangeQuery);

        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println(searchResponse.getTook());
        for (SearchHit hit : searchResponse.getHits()) {
            System.out.println(hit.getSourceAsString());
        }
        restHighLevelClient.close();
    }

    public static void main(String[] args) throws IOException {

        esIndexDocSomeQuery esIndexDocSomeQuery = new esIndexDocSomeQuery();
        //esIndexDocSomeQuery.someQuery();

        esIndexDocSomeQuery.fuzzQuery();
    }
}
