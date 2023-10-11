import { useState,useEffect } from 'react'
import React from 'react'
import MainHeader from '../MainHeader/MainHeader'
import './style.css'
import Blog from '../Blog/Blog'
import FilterCategories from '../FilteredCategories/FilteredCategories'
import axios from 'axios'
const BlogContainer = () => {
  const [blogData,setBlogData]=useState([])
  useEffect(() => {
    const getBlogData=async()=>{
      const response=await axios.get("http://localhost:9004/esg/getAll");
      console.log(response)
      setBlogData(response.data)
    }
    getBlogData()
  }, [])
  const [selectedCategories, setSelectedCategories] = useState([]);
  const categories = ['crypto', 'trading', 'NFTs','bitcoin','tech-deep-dives'];
  const handleCategoryChange = (category) => {
    if (selectedCategories.includes(category)) {
      setSelectedCategories(selectedCategories.filter((c) => c !== category));
    } else {
      setSelectedCategories([...selectedCategories, category]);
    }
  };

  const filteredItems = blogData.filter((item) =>
    selectedCategories.length === 0 ? true : selectedCategories.includes(item.category)
  );
  return (
    <div>
      <MainHeader/>
      <div className='esg-latest-articles'>
        <h2>Latest Articles</h2>
        <FilterCategories
        categories={categories}
        selectedCategories={selectedCategories}
        handleCategoryChange={handleCategoryChange}
      />
      
      </div>
      <div className='esg-articles-container'>
        <div className='esg-articles'>
        {filteredItems.map((item,index) => (
          <Blog key={index} blog={item}></Blog>
        ))}
          {/* {blogData.map((blog,index)=><Blog key={index} blog={blog}></Blog>)} */}
        </div>
      </div>
    </div>
  )
}

export default BlogContainer
