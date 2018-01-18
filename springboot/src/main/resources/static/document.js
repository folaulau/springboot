(function() {
'use strict';
console.log("Hello Document!!!");

// id must be unique
// Only document.getElementById, not anyNode.getElementById
let elem = document.getElementById("elem-content");
console.dir(elem);

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

// outerHTML
let outer = document.getElementById("outer");
console.log(outer.outerHTML);
outer.outerHTML = "<p id='newElement'>A new element!</p>";
let newElement = document.getElementById("newElement");
console.log(newElement);

// textContent
let textContent = document.getElementById("news");
console.log(textContent);
console.log(textContent.textContent);

// hidden
let hidden = document.getElementById("hidden");
hidden.hidden = false;

// html attributes
let customAttribute = document.getElementById("customAttribute");
console.log("hasAttribute: "+customAttribute.hasAttribute("color"));
console.log("getAttribute value: "+customAttribute.getAttribute("color"));

// insertion method
let div = document.createElement('div');
div.className = "alert alert-success";
div.innerHTML = "<strong>Hi there!</strong> You've read an important message.";

document.body.appendChild(div);// append as last element on body

console.log(document.body);


// appendChild
let newLi = document.createElement('li');
newLi.innerHTML = 'Hello, world!';

list.appendChild(newLi);

list.insertBefore(newLi, list.children[1]);

ol.before('before');
ol.after('after');

let prepend = document.createElement('li');
prepend.innerHTML = 'prepend';
ol.prepend(prepend);

let append = document.createElement('li');
append.innerHTML = 'append';
ol.append(append);
})();