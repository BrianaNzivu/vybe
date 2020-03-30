package com.example.vybe;

public class VisionBoardCategory {

        private String mCategories;
        private int mCategoriesImage;

        public VisionBoardCategory(String mCategories, int mCategoriesImage) {
            mCategories =mCategories;
            mCategoriesImage =mCategoriesImage;
        }

        public String  mCategories() {
            return mCategories;
        }

        public int mCategoriesImage() {
            return mCategoriesImage;
        }
    }

