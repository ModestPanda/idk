/**
 * @author Humble
 * @version 1.0
 */
public class Students {

        private String id;
        private String name;
        private Integer classname;
        private Integer dormitory;

        public Students(){

        }

        public Students(String id, String name, Integer classname, Integer dormitory) {
            this.id = id;
            this.name = name;
            this.classname = classname;
            this.dormitory = dormitory;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setClassname(Integer classname) {
            this.classname = classname;
        }

        public void setDormitory(Integer dormitory) {
            this.dormitory = dormitory;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Integer getClassname() {
            return classname;
        }

        public Integer getDormitory() {
            return dormitory;
        }
        @Override
        public String toString(){
            return "\nStudent{"+
                    "id = "+id+
                    ",name = '"+name+'\''+
                    ",classname = "+classname+
                    ",dormitory = "+dormitory+
                    '}';
        }
    }

