package tw314.drawer;

import java.util.ArrayList;
import java.util.List;

import tw314.tw314mobile.R;

/**
 * Created by Pedro on 27/09/2016.
 */
public class DrawerList {

    public static List<DrawerItem> dataList;

    public static List<DrawerItem> getDataList(){
        dataList = new ArrayList<DrawerItem>();

        // Cabecalho de categoria da lista - Ticket
        dataList.add(new DrawerItem("Ticket"));

        // Itens do Menu de Ticket
        dataList.add(new DrawerItem("Adicionar Senha", R.drawable.ic_add_white_24dp));
        dataList.add(new DrawerItem("Ver Lista de Senhas", R.drawable.ic_format_list_numbered_white_24dp));
        dataList.add(new DrawerItem("Desistir da Fila", R.drawable.ic_clear_white_24dp));

        // Cabecalho de categoria da lista - Configuracoes
        dataList.add(new DrawerItem("Configurações"));

        // Itens do Menu de Configurações
        dataList.add(new DrawerItem("Desativar Notificações", R.drawable.ic_alarm_off_white_24dp));
        dataList.add(new DrawerItem("Configurações", R.drawable.ic_settings_white_24dp));

        // Cabecalho de categoria da lista - Outros
        dataList.add(new DrawerItem(" "));

        //Itens do Menu de Outros
        dataList.add(new DrawerItem("Sair", R.drawable.ic_subdirectory_arrow_left_white_24dp));
        return dataList;
    }

    public static void setDataList(List<DrawerItem> dataList) {
        DrawerList.dataList = dataList;
    }

}
