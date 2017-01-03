SimpleRecyclerAdapter
===

This is a set of helper classes to help Android developers create a RecyclerView adapter without actually creating it everytime. You just need to create a ViewHolder class which extend `SimpleRecyclerHolder` and implement `bindView`.

You can also pass in an instance of a callback to communicate view events to your `Activity` or `Fragment`.


Here is simple implementation example using ButterKnife:

`SampleHolder.java`

    public class SampleHolder extends SimpleRecyclerHolder<Message, MessageCallback> implements View.OnClickListener {
    	@BindView(R.id.userPhoto)
    	RoundedImageView userPhoto;
    	@BindView(R.id.userName)
    	TextView userName;

    	@Keep
    	public SampleHolder(View itemView) {
    		super(itemView);
    		ButterKnife.bind(this, itemView);
    		userName.setOnClickListener(this);
    		userPhoto.setOnClickListener(this);
    	}

    	@Override
    	public void bindView(Context context, Message message) {
    		Glide.with(getContext()).load(message.getUserPhotoUrl()).into(userPhoto)
    		userName.setText(message.getFullName());
    	}

    	@Override
    	public void onClick(View v) {
    		switch (v.getId()) {
    			case R.id.userPhoto:
    			case R.id.userName: {
    				callback.onUserClick(getAdapterPosition());
    				break;
    			}
    		}
    	}
    }

`Message.java`

    public class Message {
    	String userPhotoUrl, userFullName;

    	public String getUserPhotoUrl() {
    		return userPhotoUrl;
    	}

    	public void setUserPhotoUrl(String userPhotoUrl) {
    		this.userPhotoUrl = userPhotoUrl;
    	}

    	public String getUserFullName() {
    		return userFullName;
    	}

    	public void setUserFullName(String userFullName) {
    		this.userFullName = userFullName;
    	}
    }

`MessageCallback.java`

    public interface MessageCallback {
    	void onUserClick(int position);
    }

Usage:

    SimpleRecyclerAdapter mAdapter = new SimpleRecyclerAdapter(CONTEXT, new ArrayList<Message>(), R.layout.list_item_message, new MessageCallback(){
        void onUserClick(int position){
            // clicked on user
        }
    });
