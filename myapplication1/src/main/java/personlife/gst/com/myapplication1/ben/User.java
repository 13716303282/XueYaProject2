package personlife.gst.com.myapplication1.ben;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by smz on 2018/11/22.
 */
@Entity
public class User{
        @Id(autoincrement = true)
        private Long id;//主键  自增长
        @NotNull   //不许为空
        private String name;
        private String age;
        private String content;








        public String getContent() {
                return this.content;
        }
        public void setContent(String content) {
                this.content = content;
        }
        public String getAge() {
                return this.age;
        }
        public void setAge(String age) {
                this.age = age;
        }
        public String getName() {
                return this.name;
        }
        public void setName(String name) {
                this.name = name;
        }
        public Long getId() {
                return this.id;
        }
        public void setId(Long id) {
                this.id = id;
        }
        @Generated(hash = 1079760932)
        public User(Long id, @NotNull String name, String age, String content) {
                this.id = id;
                this.name = name;
                this.age = age;
                this.content = content;
        }
        @Generated(hash = 586692638)
        public User() {
        }

}
