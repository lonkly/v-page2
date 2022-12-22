const markdownItDefault = require('markdown-it')


const down = markdownItDefault({
    html: true,
    breaks: false,
    linkify: true,
  });


  module.exports = down;