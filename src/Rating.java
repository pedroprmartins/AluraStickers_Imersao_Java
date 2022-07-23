public class Rating {
    
    private String resetStyle = "\u001b[m";
    private String piscando = "\u001b[5m";
    private String negrito = "\u001b[1m";
    private String corTxt = "\u001b[31m";
    private String corBkg = "\u001b[47m";
    private String star = "⭐";
    private String shit = "💩";
    private String good = "🥔";
    
    public String modTitulo(String titulo){
         return this.corTxt + this.corBkg + this.negrito + titulo + this.resetStyle;
    }

    public String urlImagem(String urlImagem){
        return urlImagem;
    }
    
    public String ratingMovie(double nota) {

        if(nota >= 8.0){
            for(int count = 0; count < 5; count++){
                return star;
            }
            return corBkg + negrito + piscando + nota + resetStyle;

        }else if(nota < 8.0 && nota >= 7.0){
            for(int count = 0; count < 5; count++){
                return good;
            }
            return corBkg + negrito + piscando + nota + resetStyle;

        }else if(nota < 7.0){
            for(int count = 0; count < 5; count++){
                return shit;
            }
            return corBkg + negrito + piscando + nota + resetStyle;
        }
        return null; 
    }
}
