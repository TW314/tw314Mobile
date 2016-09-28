package tw314.drawer;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

// SubPackage do Drawer precisa fazer import da class R do Package principal
import tw314.tw314mobile.R;

public class CustomDrawerAdapter extends ArrayAdapter<DrawerItem> {

    // Contexto sendo passado para a classe, lista de items e ID do tipo de lista
    Context context;
    List<DrawerItem> drawerItemList;
    int layoutResID;

    public CustomDrawerAdapter(Context context, int layoutResourceID,
                               List<DrawerItem> listItems) {
        super(context, layoutResourceID, listItems);
        this.context = context;
        this.drawerItemList = listItems;
        this.layoutResID = layoutResourceID;

    }

    // Override que retorna View em Adapter para a Lista no MainLayoutActivity
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        DrawerItemHolder drawerHolder;
        View view = convertView;

        if (view == null) {
            // Cria Inflador de Layout com Activity passada ~MainLayoutActivity
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            drawerHolder = new DrawerItemHolder();

            // Cria view de adapter
            view = inflater.inflate(layoutResID, parent, false);

            // Inicializa os atributos do DrawerHolder
            // Itens da Lista
            drawerHolder.ItemName = (TextView) view.findViewById(R.id.drawer_itemName);
            drawerHolder.icon = (ImageView) view.findViewById(R.id.drawer_icon);

            // Cabecalho
            drawerHolder.title = (TextView) view.findViewById(R.id.drawerTitle);

            // Passa as posicoes dos itens (Cabecalho e Corpo)
            drawerHolder.headerLayout = (LinearLayout) view.findViewById(R.id.headerLayout);
            drawerHolder.itemLayout = (LinearLayout) view.findViewById(R.id.itemLayout);

            view.setTag(drawerHolder);
        } else {
            drawerHolder = (DrawerItemHolder) view.getTag();
        }

        // Pega item atual da lista
        DrawerItem dItem = (DrawerItem) this.drawerItemList.get(position);

        // Controla qual visibilidade deve ser mostrada em cada parte do NavDrawer
        if (dItem.getTitle() != null){
            // Cabecalho
            drawerHolder.headerLayout.setVisibility(LinearLayout.VISIBLE);
            drawerHolder.itemLayout.setVisibility(LinearLayout.INVISIBLE);
            drawerHolder.title.setText(dItem.getTitle());
        } else {
            // Menu
            drawerHolder.headerLayout.setVisibility(LinearLayout.INVISIBLE);
            drawerHolder.itemLayout.setVisibility(LinearLayout.VISIBLE);

            drawerHolder.icon.setImageDrawable(view.getResources().getDrawable(
                    dItem.getImgResID()));
            drawerHolder.ItemName.setText(dItem.getItemName());
        }
        return view;
    }

    private static class DrawerItemHolder {
        TextView ItemName, title;
        ImageView icon;
        LinearLayout headerLayout, itemLayout;
    }
}