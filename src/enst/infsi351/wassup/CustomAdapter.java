package enst.infsi351.wassup;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class CustomAdapter extends PagerAdapter{
	Context context;
	int[] imageId = {R.drawable.affiche_1, R.drawable.affiche_2, R.drawable.affiche_3, R.drawable.affiche_4, R.drawable.affiche_5};
	
	public CustomAdapter(Context acceuilFragment){
        this.context = acceuilFragment;
         
    }
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imageId.length;
	}
	

	@Override
	public boolean isViewFromObject(View view, Object object) {
		// TODO Auto-generated method stub
		return view == ((View)object);
	}
	@Override
    public Object instantiateItem(ViewGroup container, int position) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        
        View viewItem = inflater.inflate(R.layout.image_item, container, false);
        ImageView imageView = (ImageView) viewItem.findViewById(R.id.imageView);
        imageView.setImageResource(imageId[position]);
        ((ViewPager)container).addView(viewItem);
        
        return viewItem;
        // TODO Auto-generated method stub
         
    }
	
	@Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        ((ViewPager) container).removeView((View) object);
    }

}
