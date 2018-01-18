(function() {
'use strict';
console.log("Hello Document!!!");

// id must be unique
// Only document.getElementById, not anyNode.getElementById
let elem = document.getElementById("elem-content");
console.log(elem.innerHTML);

// getElementsByClassName
// getElementsByName
let inputs = table.getElementsByTagName('input');

for (let input of inputs) {
  console.log( input.value + ': ' + input.checked );
}

// find by name attribute
let form = document.getElementsByName('my-form')[0];

// find by class inside the form
let articles = form.getElementsByClassName('article');
console.log(articles.length); // 2, found two elements with class "article"

let elements = document.querySelectorAll('ul > li:last-child');

for (let elem of elements) {
	console.log(elem.innerHTML); // "test", "passed"
}

// closest
let chapter = document.querySelector('.chapter'); // LI

console.log(chapter.closest('.book')); // UL
console.log(chapter.closest('.contents')); // DIV

console.log(chapter.closest('h1')); // null (because h1 is not an ancestor)
})();