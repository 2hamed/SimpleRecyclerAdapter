import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Hamed on 2/20/16.in NumbersNew
 */
public abstract class SimpleRecyclerHolder<Item, Callback> extends RecyclerView.ViewHolder {
	protected Callback callback;
	protected Context mContext;
	
	public SimpleRecyclerHolder(View itemView) {
		super(itemView);
		mContext = itemView.getContext();
	}

	public void setCallback(Callback callback) {
		this.callback = callback;
	}

	public abstract void bindView(Context context, Item item);

	public void onRecycle() {
	}

	protected Context getContext() {
		return mContext;
	}
}
