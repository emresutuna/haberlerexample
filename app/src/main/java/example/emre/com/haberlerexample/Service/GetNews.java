package example.emre.com.haberlerexample.Service;
import example.emre.com.haberlerexample.Models.NewsModel;
import retrofit2.Call;
import retrofit2.http.GET;
public interface GetNews {
    @GET("services/haberlercom/2.11/service.asmx/haberler?category=manset&count=35&offset=0&deviceType=1&userId=61ed99e0c09a8664")
    Call<NewsModel> getNews();
}
