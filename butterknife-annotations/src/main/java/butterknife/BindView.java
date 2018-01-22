package butterknife;

import android.support.annotation.IdRes;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Bind a field to the view for the specified ID. The view will automatically be cast to the field
 * type.
 * <pre><code>
 * {@literal @}BindView(R.id.title) TextView title;
 * </code></pre>
 */
@Retention(CLASS) //注解的保留时间，可选值 SOURCE(源码时)，CLASS(编译时)，RUNTIME(运行时),默认为 CLASS
@Target(FIELD) //表示可以用来修饰哪些程序元素，如TYPE，METHOD，CONSTRUCTOR，FIELD，PARAMETER等，未标注则表示可修饰所有
public @interface BindView {
    /**
     * View ID to which the field will be bound.
     */
    @IdRes int value();
}







































