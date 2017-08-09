package traco.sample;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Api apiService;
    private WebView webView;
    String sr = "";
    String ideToken = "eAA0AGsANwBmAGcATABhADkAcAA3AFMAdQA2ADQALwBlAE8AWABwAHAAcgBYAFkAbABNAGkAUgBhAGUANwBjAEMATQBtAFkAeABrAGUATQAxAG4AVgA3AEsASwB4ADYAbg\n" +
            "\n" +
            "AyAGMAWgBrADIAWgBrAGcARwB1AFIAMABZAG0AZgBwAFcAZgAwAHYATABMAEMAWQBlAE4AMwBXAE4ATwAwADIANQB3AEgA\n" +
            "\n" +
            "VgBHAHoAQQBrAEYAUQBkAG0ATgBZAGEAbAAzAGEAQgBnAGkAcgAvADUAVwAxAEQAZQBqAGgAUwA1AHUAaAB2AHAAeAB5AF\n" +
            "\n" +
            "YAOAB0AHcAUQA5AGwAbQBKAHgAVwBvAEoAOABJAFMASwA4ADMAaQBXAEIARwBuAEQARQBvAHYAZgBnAHAATgBBAHcAbwB6\n" +
            "\n" +
            "AFYAKwAzAHkATAA1ADkAWgBKAHQATQBYAGQAOQBhAGgAWgA3ADEATgBMAEUAOQBlAGMAYwB3AG8ATAB2AHEAMwAxAG4AWA\n" +
            "\n" +
            "B4AHIAawBuAFoALwBvAEUAQQBqADUAUwBPAHUAbgArADcARABtAHEAbwBRAEcAZQB0AFcAbwBhAGgANwBHAEUAUgBIAHoA\n" +
            "\n" +
            "NQBjAGoAdABXAEUALwAzAEMAZwB2ADAAbgB2AEMANQBCADkAcgByAHcAVgBoAFEATwBRAHgAaQA4AEYAdwBVAHcAeQBrAE\n" +
            "\n" +
            "0AYwB3AFMARABXAGYANQBjADQAaQB6AFIAZQB2AFAATgBkADUASgA2AFkARwB3AEEAbABBAFUAeQBIAFYAMQB1AC8AVwBS\n" +
            "\n" +
            "AGoAYwB3AD0A";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webView);
        new AsyncTaskRunner().execute();
    }

    public void getUserCredentials() throws UnsupportedEncodingException {
        apiService = ApiManager.getClient().create(Api.class);
        Call<Object> call = apiService.hostedFunding(URLEncoder.encode("7524", "utf-8"),
                URLEncoder.encode("Mike", "utf-8"), URLEncoder.encode("Test", "utf-8"),
                URLEncoder.encode("7025800000", "utf-8"), URLEncoder.encode("mwidner@sightlinepayments.com", "utf-8"),
                URLEncoder.encode("100+S+Eastern+Ave", "utf-8"),
                URLEncoder.encode("Las+Vegas", "utf-8"),
                URLEncoder.encode("NV", "utf-8"),
                URLEncoder.encode("US", "utf-8"),
                URLEncoder.encode("89123", "utf-8"),
                URLEncoder.encode(ideToken, "utf-8"),
                URLEncoder.encode("USFTEST", "utf-8"),
                URLEncoder.encode("UFT01", "utf-8"),
                URLEncoder.encode("080420171226", "utf-8"),
                URLEncoder.encode("http://www.merchantsite.com", "utf-8"),
                URLEncoder.encode("US+Fantasy", "utf-8"));
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                System.out.println(" response = " + response.body());
                ;
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    void setHttpUrlConnection() throws IOException {
        String urlParameters = "MemberNumber=7524&FirstName=Mike&LastName=Test&PhoneNumber=7025800000&Email=mwidner\n" +
                "\n" +
                "\n" +
                "%40sightlinepayments.com&Address=100+S+Eastern+Ave&City=Las\n" +
                "\n" +
                "+Vegas&State=NV&Country=US&PostalCode=89123&IdentityToken=eAA0AGsANwBmAGcATABhADkAcAA3AFMAdQA2\n" +
                "\n" +
                "ADQALwBlAE8AWABwAHAAcgBYAFkAbABNAGkAUgBhAGUANwBjAEMATQBtAFkAeABrAGUATQAxAG4AVgA3AEsASwB4ADYAbg\n" +
                "\n" +
                "AyAGMAWgBrADIAWgBrAGcARwB1AFIAMABZAG0AZgBwAFcAZgAwAHYATABMAEMAWQBlAE4AMwBXAE4ATwAwADIANQB3AEgA\n" +
                "\n" +
                "VgBHAHoAQQBrAEYAUQBkAG0ATgBZAGEAbAAzAGEAQgBnAGkAcgAvADUAVwAxAEQAZQBqAGgAUwA1AHUAaAB2AHAAeAB5AF\n" +
                "\n" +
                "YAOAB0AHcAUQA5AGwAbQBKAHgAVwBvAEoAOABJAFMASwA4ADMAaQBXAEIARwBuAEQARQBvAHYAZgBnAHAATgBBAHcAbwB6\n" +
                "\n" +
                "AFYAKwAzAHkATAA1ADkAWgBKAHQATQBYAGQAOQBhAGgAWgA3ADEATgBMAEUAOQBlAGMAYwB3AG8ATAB2AHEAMwAxAG4AWA\n" +
                "\n" +
                "B4AHIAawBuAFoALwBvAEUAQQBqADUAUwBPAHUAbgArADcARABtAHEAbwBRAEcAZQB0AFcAbwBhAGgANwBHAEUAUgBIAHoA\n" +
                "\n" +
                "NQBjAGoAdABXAEUALwAzAEMAZwB2ADAAbgB2AEMANQBCADkAcgByAHcAVgBoAFEATwBRAHgAaQA4AEYAdwBVAHcAeQBrAE\n" +
                "\n" +
                "0AYwB3AFMARABXAGYANQBjADQAaQB6AFIAZQB2AFAATgBkADUASgA2AFkARwB3AEEAbABBAFUAeQBIAFYAMQB1AC8AVwBS\n" +
                "\n" +
                "AGoAYwB3AD0A&MerchantID=USFTEST&MerchantApplication=UFT01&MerchantTransactionID=080420171226&R\n" +
                "\n" +
                "eturnURL=http://www.merchantsite.com&MerchantName=US+Fantasy";
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;
        String request = "https://ceapspan.slpuat.com/hostedfunding";
        URL url = new URL(request);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setInstanceFollowRedirects(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        conn.setUseCaches(false);
        try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
            System.out.println("postData = " + postData);
            wr.write(postData);
            sr = wr.toString();
        }
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            try {
                setUrlConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sr;
        }


        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            progressDialog.dismiss();
        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this,
                    "ProgressDialog",
                    "Wait for seconds");
        }


    }

    void setUrlConnection() throws IOException {
        String url = "https://ceapspan.slpuat.com/hostedfunding";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        String urlParameters = "MemberNumber=7524&FirstName=Mike&LastName=Test&PhoneNumber=7025800000&Email=mwidner\n" +
                "\n" +
                "\n" +
                "%40sightlinepayments.com&Address=100+S+Eastern+Ave&City=Las\n" +
                "\n" +
                "+Vegas&State=NV&Country=US&PostalCode=89123&IdentityToken=eAA0AGsANwBmAGcATABhADkAcAA3AFMAdQA2\n" +
                "\n" +
                "ADQALwBlAE8AWABwAHAAcgBYAFkAbABNAGkAUgBhAGUANwBjAEMATQBtAFkAeABrAGUATQAxAG4AVgA3AEsASwB4ADYAbg\n" +
                "\n" +
                "AyAGMAWgBrADIAWgBrAGcARwB1AFIAMABZAG0AZgBwAFcAZgAwAHYATABMAEMAWQBlAE4AMwBXAE4ATwAwADIANQB3AEgA\n" +
                "\n" +
                "VgBHAHoAQQBrAEYAUQBkAG0ATgBZAGEAbAAzAGEAQgBnAGkAcgAvADUAVwAxAEQAZQBqAGgAUwA1AHUAaAB2AHAAeAB5AF\n" +
                "\n" +
                "YAOAB0AHcAUQA5AGwAbQBKAHgAVwBvAEoAOABJAFMASwA4ADMAaQBXAEIARwBuAEQARQBvAHYAZgBnAHAATgBBAHcAbwB6\n" +
                "\n" +
                "AFYAKwAzAHkATAA1ADkAWgBKAHQATQBYAGQAOQBhAGgAWgA3ADEATgBMAEUAOQBlAGMAYwB3AG8ATAB2AHEAMwAxAG4AWA\n" +
                "\n" +
                "B4AHIAawBuAFoALwBvAEUAQQBqADUAUwBPAHUAbgArADcARABtAHEAbwBRAEcAZQB0AFcAbwBhAGgANwBHAEUAUgBIAHoA\n" +
                "\n" +
                "NQBjAGoAdABXAEUALwAzAEMAZwB2ADAAbgB2AEMANQBCADkAcgByAHcAVgBoAFEATwBRAHgAaQA4AEYAdwBVAHcAeQBrAE\n" +
                "\n" +
                "0AYwB3AFMARABXAGYANQBjADQAaQB6AFIAZQB2AFAATgBkADUASgA2AFkARwB3AEEAbABBAFUAeQBIAFYAMQB1AC8AVwBS\n" +
                "\n" +
                "AGoAYwB3AD0A&MerchantID=USFTEST&MerchantApplication=UFT01&MerchantTransactionID=080420171226&R\n" +
                "\n" +
                "eturnURL=http://www.merchantsite.com&MerchantName=US+Fantasy";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        sr = response.toString();
        //print result
        System.out.println(response.toString());

    }
}
