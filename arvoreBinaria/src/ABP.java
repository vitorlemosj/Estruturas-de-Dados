public class ABP<T> {
    NoTriplo<T> raiz;

    public ABP(){
        raiz=null;
    }
    NoTriplo<T> getRaiz(){
        return raiz;
    }
    public void inserir(T dado) {
        NoTriplo<T> novoNo = new NoTriplo<T>();
        novoNo.setDado(dado);
        NoTriplo<T> aux = raiz;

        while(true){
            if((Integer)dado <= (Integer) raiz.getDado()){
                if(aux.getEsquerda()!=null){

                    aux=aux.getEsquerda();
                }else{

                    novoNo.setGenitor(aux);
                    aux.setEsquerda(novoNo);
                    break;
                }
            }else{

                if( aux.getDireita()!=null){

                    aux=aux.getDireita();
                }else{

                    novoNo.setGenitor(aux);
                    aux.setDireita(novoNo);
                    break;
                }
            }
        }

    }












}