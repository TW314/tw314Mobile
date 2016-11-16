package tw314.tw314mobile.faq;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pedro on 06/10/2016.
 */

public class FaqList {

    // ArrayList que armazena os itens da lista
    private static List<FaqItem> faqList = new ArrayList<>();

    public FaqList() {
    }

    public static void setFaqList(ArrayList<FaqItem> faqList) {
        FaqList.faqList = faqList;
    }

    public static List<FaqItem> getFaqList() {
        faqList.add(new FaqItem("Adicionar Senha", "Aprenda como adicionar mais uma senha ao aplicativo, para fazer acompanhamento"));
        faqList.add(new FaqItem("Configurar Notificações", "Aprenda como configurar frequência e insistência das notificações"));
        faqList.add(new FaqItem("Cores da Ampulheta", "Aprenda o que as cores da ampulheta na tela principal informam"));
        faqList.add(new FaqItem("Chamada", "Saiba como o aplicativo irá lhe avisar quando sua vez estiver chegando"));
        faqList.add(new FaqItem("Funcionamento", "Saiba como o aplicativo funciona e trabalha"));
        faqList.add(new FaqItem("Sugestões", "Descubra onde fazer sugestões para o aprimoramento do sistema feito para você"));
        faqList.add(new FaqItem("Toque de Notificação", "Aprenda a escolher um novo toque para receber notificações"));
        return faqList;
    }

}
