import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // API IMDB
        //String url = "https://imdb-api.com/en/API/Top250Movies/k_0ojt0yvm";

        // API Fornecida pela comunidade da Alura
        //Top 250
        /* 
            https://api.mocki.io/v2/549a5d8b/Top250Movies
            https://api.mocki.io/v2/549a5d8b/MostPopularMovies
            https://api.mocki.io/v2/549a5d8b/MostPopularTVs
            https://api.mocki.io/v2/549a5d8b/Top250TVs
         */
        String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        String body = response.body();

        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        
        var generate = new StickersGenerator();
        for (Map<String,String> filme : listaDeFilmes) {
            String urlImagem = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = titulo + ".png";

            generate.cria(inputStream, nomeArquivo, titulo);

            System.out.println(filme.get("title"));
            /*
            if ( Float.parseFloat(filme.get("imDbRating")) <= 5 ) {
                System.out.println("\uD83C\uDF45 " + filme.get("imDbRating"));
            }
            else {
                System.out.println("\u2B50 " + filme.get("imDbRating"));
            }
            */
        }
    }
}
