package net.promasoft.trawellmate.argapp;

public class DataSearch {

        private String name, pDescription;

        public DataSearch(String packageName, String packageDescriptn) {
            this.name = packageName;
            this.pDescription = packageDescriptn;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return pDescription;
        }

        public void setDescription(String pDescription) {
            this.pDescription = pDescription;
        }

}
