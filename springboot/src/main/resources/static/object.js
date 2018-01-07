(function() {
'use strict';
console.log("Hello Object!!!");

let person = {
	name: "Kinga",
	age: 4,
	"nick name": "wuwu",
	printName(){
		return "print name";
	}
}
console.log("name: "+person.name+", nickname: "+person["nick name"]+", printName: "+person.printName());

let person1 = new Object();
person1.name = "Folau";
person1["age"] = 5;
// computed property
let type = "employee";
person1[type] = "fulltime";

console.log("name1: "+person1.name+", age1: "+person1["age"]+", type: "+person1[type]);


// object with function
function makeUser(name, age) {
	let salary = age * 12;
	// return an object
	return {
	    name: name,
	    age: age,
	    getSalary(){
	    		return salary;
	    	},
	    getBill(){
	    		return age * 5;
	    	}
	};
}
console.log("makeUser")
let lisa = makeUser("Lisa", 30);
console.log("name: "+lisa.name+", age: "+lisa["age"]+", salary: "+lisa.getSalary()+", bill: "+lisa.getBill());

// check if attribute exists
console.log("name? "+("name" in person));
console.log("height? "+("height" in person));
console.log("");

// copy by clone
console.log("Clone");
let ati = {};
console.log("Clone with for in (deep clone)");
for(let key in lisa){
	ati[key] = lisa[key];
}
console.log("name: "+ati["name"]);

let lupe = {};
console.log("Clone with Object.assign (shallow clone), existing attributes will be overriden");

Object.assign(lupe, lisa);
console.log(lupe);
console.log();

// constructor function
console.log("Constructor function");
function User(name, age) {
	this.name = name;
	this.age = age;
	
	this.getSalary = function(){
		return age * 34;
	}
}

let kinga = new User("Kinga", 4);
console.log("name: "+kinga.name+", age: "+kinga.age+", salary: "+kinga.getSalary())
})();