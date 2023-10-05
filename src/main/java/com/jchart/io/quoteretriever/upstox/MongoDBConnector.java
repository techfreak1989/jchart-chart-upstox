package com.jchart.io.quoteretriever.upstox;

import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.jchart.model.mongodb.TokenResponse;
import com.jchart.model.mongodb.TradeIndex;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDBConnector {

	public static String getToken() {
//		return <API_TOKEN>;
        System.out.println("test mongodb");
		String uri = "mongodb://localhost:27017/?";
        try (MongoClient mongoClient = MongoClients.create(uri)) {

            CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
            CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
            
            MongoDatabase database = mongoClient.getDatabase("test").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<TokenResponse> collection = database.getCollection("tokenResponse", TokenResponse.class);
            TokenResponse doc = collection.find(eq("_id", "token")).first();
            mongoClient.close();
            if (doc != null) {
                return doc.getAccess_token();
            } else {
                System.out.println("No matching documents found.");
                throw new IllegalAccessError("no token found in mongodb");
            }
        } 
	}
	


	public static TradeIndex getTradeBySymbol(String symbol) {
        System.out.println("test mongodb");
		String uri = "mongodb://localhost:27017/?";
        try (MongoClient mongoClient = MongoClients.create(uri)) {

            CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
            CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
            
            MongoDatabase database = mongoClient.getDatabase("test").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<TradeIndex> collection = database.getCollection("bse", TradeIndex.class);
            TradeIndex doc = collection.find(eq("tradingsymbol", symbol.toUpperCase())).first();
//            collection.in
            if (doc != null) {
                System.out.println(doc);
                return doc;
            } else {
                System.out.println("No matching documents found for "+symbol);
                return getTradeBySymbol("OIL");
//                throw new IllegalAccessError("no token found in mongodb");
            }
        }
	}
}
