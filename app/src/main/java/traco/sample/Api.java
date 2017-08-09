package traco.sample;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by tikson.tom on 5/31/2017.
 */

public interface Api {
    @FormUrlEncoded
    @POST("hostedfunding")
    Call<Object> hostedFunding(@Field("MemberNumber") String MemberNumber, @Field("FirstName") String FirstName,
                               @Field("LastName") String LastName,@Field("PhoneNumber") String PhoneNumber,
                               @Field("Email") String Email, @Field("Address") String Address,
                               @Field("City") String City, @Field("State") String State,
                               @Field("Country") String Country, @Field("PostalCode") String PostalCode,
                               @Field("IdentityToken") String IdentityToken, @Field("MerchantID") String MerchantID,
                               @Field("MerchantApplication") String MerchantApplication, @Field("MerchantTransactionID") String MerchantTransactionID,
                               @Field("ReturnURL") String ReturnURL, @Field("MerchantName") String MerchantName);


}
