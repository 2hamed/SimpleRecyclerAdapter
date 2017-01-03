import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;
import java.util.List;

public class SimpleRecyclerAdapter<Item, Callback> extends RecyclerView.Adapter<SimpleRecyclerHolder> {
	private static final String TAG = "SimpleRecyclerAdapter";
	private final Class<?> cls;
	private WeakReference<Context> mContext;
	private List<Item> items;
	private int layout;
	private Callback callback;


	public SimpleRecyclerAdapter(Context context,
	                             List<Item> items,
	                             Class<? extends SimpleRecyclerHolder<Item, Callback>> cls,
	                             @LayoutRes int layout, Callback callback) {
		this.cls = cls;
		this.mContext = new WeakReference<>(context);
		this.items = items;
		this.layout = layout;
		this.callback = callback;
	}

	public void updateItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public SimpleRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(mContext.get()).inflate(layout, parent, false);
		SimpleRecyclerHolder<Item, Callback> viewHolder;
		try {
			viewHolder = (SimpleRecyclerHolder<Item, Callback>) cls.getConstructor(View.class).newInstance(view);
			viewHolder.setCallback(callback);
		} catch (Exception e) {
			throw new RuntimeException("Error creating ViewHolder:", e);
		}

		return viewHolder;
	}

	@Override
	public void onBindViewHolder(SimpleRecyclerHolder holder, int position) {
		holder.bindView(mContext.get(), items.get(position));
	}

	@Override
	public void onViewRecycled(SimpleRecyclerHolder holder) {
		super.onViewRecycled(holder);
		holder.onRecycle();
	}

	@Override
	public int getItemCount() {
		return items.size();
	}

}
