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
 //_____fazer uma conexão HTTP e buscar os top 250 filmes 
        
    	String url = "https://api.themoviedb.org/3/trending/movie/week?api_key=872995efee79646f5b0d834c33522673";
    	String prefixo = "https://image.tmdb.org/t/p/w500";
        
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

//______extrair só os dados que interessam (titulo, poster, classificação)     
       
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

//______exibir e manipular os dados

        var titulo = new Rating();
        var urlImagem = new Rating();
        var estrelas = new Rating();
        var geradora = new GeradoraDeFigurinhas();
        
        for (Map<String,String> filme : listaDeFilmes) {

            String urlPathImage = urlImagem.urlImagem(prefixo + filme.get("backdrop_path"));
            String titleMovie = titulo.modTitulo(filme.get("title"));
            estrelas.ratingMovie(filme.get("vote_average"));

            // InputStream inputStream = new URL(urlPathImage).openStream();
            // String nomeArquivo = titleMovie + ".png";
            // geradora.cria(inputStream, nomeArquivo);

            System.out.println(titleMovie);
            System.out.println(estrelas);
            System.out.println();
        }
    }
}
