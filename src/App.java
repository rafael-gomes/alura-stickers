import java.io.InputStream;
import java.net.URL;
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
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        // API Nasa
        //String url = "https://api.nasa.gov/planetary/apod?api_key=o1vdnwDbBCSDkToNXXBnzWjgNCR3dVdgThwl0XOP&start_date=2022-06-12&end_date=2022-06-14";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        List<Conteudo> conteudos = extrator.extraiConteudos(json);
        
        var generate = new StickersGenerator();
        
        for (int i = 0; i < conteudos.size(); i ++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = conteudo.getTitulo() + ".png";

            generate.cria(inputStream, nomeArquivo, conteudo.getTitulo());

            System.out.println(conteudo.getTitulo());
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
