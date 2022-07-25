import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        //String url = "https://api.nasa.gov/planetary/apod?api_key=noe8ExJbnL6x36oO2HLqn6g4F7gFu2jFDxms27EH&start_date=2022-07-20&end_date=2022-07-23";
        String url = "http://localhost:8080/linguagens";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();
        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        //Exibir e manipular os dados
        //ExtratorDeConteudoDaNasa extrator = new ExtratorDeConteudoDaNasa();

        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradorDeFigurinhas();

        for (int i = 0; i < 2; i++) {
            Conteudo conteudo = conteudos.get(i);


            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println("Título: " + conteudo.getTitulo());
            System.out.println();
        }
    }
}