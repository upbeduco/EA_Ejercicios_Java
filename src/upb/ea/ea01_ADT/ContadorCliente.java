package upb.ea.ea01_ADT;

class ContadorCliente implements Contador {

    private int conteo;
    private String id;


    public static void main(String[] args) {
        System.out.println("Ejemplo implementacion ADT Contador");
        
    }



    @Override
    public void incrementar() {
        conteo++;
    }



    @Override
    public int getConteo() {
        return conteo;
    }



    @Override
    public String getId() {
        return id;
    }

}