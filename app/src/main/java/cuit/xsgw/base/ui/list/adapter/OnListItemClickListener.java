package cuit.xsgw.base.ui.list.adapter;

import android.view.View;

/**
 * List条目点击监听事件.
 */

public interface OnListItemClickListener {

    /**
     * 条目被点击
     *
     * @param view     被点击的View
     * @param position 被点击的条目
     */
    void onItemClick(View view, int position);
}
