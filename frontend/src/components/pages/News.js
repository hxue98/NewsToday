import React, { useState, useEffect } from 'react';
import axios from 'axios';
import configuration, { getAuthorizedConfig } from '../../config';
import './News.css';

const News = (props) => {
  const [news, setNews] = useState({
    id: props.match.params.id,
    title: '',
    category: '',
    summary: '',
    url: '',
    date: '',
    source: '',
    imageUrl: '',
    loading: true,
  });

  useEffect(() => {
    axios.get(configuration.api + '/news/id?id=' + news.id, getAuthorizedConfig()).then((res) => {
      setNews({
        ...res.data.result,
        loading: false
      });
    }).catch((err) => {
      console.log(err);
    });
  }, [news.id]);

  const { title, category, summary, url, date, source, imageUrl } = news;

  // const onChange = (e) => {
  //   setNews({ ...news, [e.target.name]: e.target.value });
  // };

  if (news.loading) {
    return <div className='loading'></div>;
  }

  return (
    <div>
      <h1>{title}</h1>
      <h5>{category}</h5>
      <a href={url}>READ MORE AT {source}</a>
      <h5>{date}</h5>
      <br/>
      <p>{summary}</p>
      <img url={imageUrl} alt={'image from ' + source}></img>
    </div>);
};

export default News;
