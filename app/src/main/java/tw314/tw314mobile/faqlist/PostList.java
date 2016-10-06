package tw314.tw314mobile.faqlist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pedro on 06/10/2016.
 */

public class PostList {

    // ArrayList que armazena os itens da lista
    private static List<PostItem> postArrayList = new ArrayList<>();

    public PostList() {
    }

    public static void setPostArrayList(ArrayList<PostItem> postArrayList) {
        PostList.postArrayList = postArrayList;
    }

    public static List<PostItem> getPostArrayList() {
        postArrayList.add(new PostItem("Adicionar Senha", "Aprenda como adicionar mais uma senha ao aplicativo, para fazer acompanhamento"));
        postArrayList.add(new PostItem("Configurar Notificações", "Aprenda como configurar frequência e insistência das notificações"));
        postArrayList.add(new PostItem("Cores da Ampulheta", "Aprenda o que as cores da ampulheta na tela principal informam"));
        postArrayList.add(new PostItem("Chamada", "Saiba como o aplicativo irá lhe avisar quando sua vez estiver chegando"));
        postArrayList.add(new PostItem("Funcionamento", "Saiba como o aplicativo funciona e trabalha"));
        postArrayList.add(new PostItem("Sugestões", "Descubra onde fazer sugestões para o aprimoramento do sistema feito para você"));
        postArrayList.add(new PostItem("Toque de Notificação", "Aprenda a escolher um novo toque para receber notificações"));
        return postArrayList;
    }

}
