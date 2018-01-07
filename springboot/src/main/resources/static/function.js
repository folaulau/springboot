(function() {
'use strict';
console.log("Hello Javascript!!!");

//sayHi("Laulau");//error -> sayHi is not yet defined.

// function expression
let sayHi = function(name){
	console.log("Hello "+name+"!!!");
}

sayHi("Laulau");

// function declaration
function sayHello(name){
	console.log("Hello "+name+"!!!");
}

sayHello("Fusi");


// arrow function
let add = function(a, b) {
	return a + b;
}
console.log("3 + 4 = "+add(3,4));

let add1 = (a,b) => {
	let result = a + b;
	return result;
};
console.log("3 + 4 = "+add1(3,4));

let sayHiNoArg = () => console.log("Hi from no argument!");
sayHiNoArg();


// callback function
function ask(question, sayYes, sayNo) {
	console.log(question);
	var answer = "yes";
	if(answer == "yes")
		sayYes();
	else 
		sayNo();
}

function showOk() {
	console.log("You agreed.");
}

function showCancel() {
	console.log("You canceled the execution.");
}

ask("Do you agree?", showOk, showCancel);

//callback with annonymous function
ask("Do you agree?", function() { console.log("You agreed."); }, function() { console.log("You agreed."); });


})();