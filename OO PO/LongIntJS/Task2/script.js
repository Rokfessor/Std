function calculate(){
	var operation = document.getElementById("select").value;
	var a = document.getElementById("firstNumber").value;
	var b = document.getElementById("secondNumber").value;
	var res = "";
	switch (operation) {
		case "+": res = LongInt.sum(a,b); break;
		case "-": res = LongInt.diff(a,b); break;
		case "*": res = LongInt.multiply(a,b); break; 
		case "/": res = LongInt.div(a,b); break; 
		case "%": res = LongInt.mod(a,b); break; 
		case "=": res = LongInt.equals(a,b); break;
		case ">": res = LongInt.greater(a,b); break;
		case "<": res = LongInt.less(a,b); break;
		case ">=": res = LongInt.greaterOrEquals(a,b); break; 
		case "<=": res = LongInt.lessOrEquals(a,b); break; 
			
	}
	document.getElementById("label").innerHTML = res;

	for (let i = 0; i < 50; i++){
		var a = Math.trunc(Math.random() * 100000) - Math.trunc(Math.random() * 100000);
		var b = Math.trunc(Math.random() * 100000) - Math.trunc(Math.random() * 100000);
		var sA = new LongInt(a);
		var sB = new LongInt(b);

		/*console.log(sA.toString() + "+" + sB.toString() + "=" + LongInt.sum(a, b) + "|" + (a+b));
		console.log(sA.toString() + "-" + sB.toString() + "=" + LongInt.diff(a, b) + "|" + (a-b));
		console.log(sA.toString() + "*" + sB.toString() + "=" + LongInt.multiply(a, b) + "|" + (a*b));*/
		/*console.log(sA.toString() + "/" + sB.toString() + "=" + LongInt.div(a, b) + "|" + Math.trunc(a/b));
		console.log(sA.toString() + "%" + sB.toString() + "=" + LongInt.mod(a, b) + "|" + (a%b));
		console.log(sA.toString() + "=" + sB.toString() + "=" + LongInt.equals(a, b) + "|" + (a==b));
		console.log(sA.toString() + ">" + sB.toString() + "=" + LongInt.greater(a, b) + "|" + (a>b));
		console.log(sA.toString() + "<" + sB.toString() + "=" + LongInt.less(a, b) + "|" + (a<b));
		console.log(sA.toString() + ">=" + sB.toString() + "=" + LongInt.greaterOrEquals(a, b) + "|" + (a>=b));
		console.log(sA.toString() + "<=" + sB.toString() + "=" + LongInt.lessOrEquals(a, b) + "|" + (a<=b));
		console.log("==============");*/
	}
}

class LongInt{
	constructor(numb){
		this.num = numb.toString();
	}

	toString(){
		return this.num;
	}

	sum(b){
		if (b instanceof LongInt)
			b = b.toString();

		return LongInt.sum(this.num, b);
	}
	diff(b){
		if (b instanceof LongInt)
			b = b.toString();

		return LongInt.diff(this.num, b);
	}
	multiply(b){
		if (b instanceof LongInt)
			b = b.toString();

		return LongInt.multiply(this.num, b);
	}
	div(b){
		if (b instanceof LongInt)
			b = b.toString();

		return LongInt.div(this.num, b);
	}
	mod(b){
		if (b instanceof LongInt)
			b = b.toString();

		return LongInt.mod(this.num, b);
	}
	equals(b){
		if (b instanceof LongInt)
			b = b.toString();

		return LongInt.equals(this.num, b);
	}
	greater(b){
		if (b instanceof LongInt)
			b = b.toString();

		return LongInt.greater(this.num, b);
	}
	less(b){
		if (b instanceof LongInt)
			b = b.toString();

		return LongInt.less(this.num, b);
	}
	greaterOrEquals(b){
		if (b instanceof LongInt)
			b = b.toString();

		return LongInt.greaterOrEquals(this.num, b);
	}
	lessOrEquals(b){
		if (b instanceof LongInt)
			b = b.toString();

		return LongInt.lessOrEquals(this.num, b);
	}

	static multiply(a, b) {
		a = a.toString();
		b = b.toString();

		var negative = false;
		if (a[0] == '-'){
			a = a.substring(1);
			negative = !negative;
		}
		if (b[0] == '-'){
			b = b.substring(1);
			negative = !negative;
		}
		var max = a, min = b, ost =0;
		if (LongInt.greater(min, max)){
			[max, min] = [min, max];
		}

		var res = "0";
		for (let i = min.length - 1; i >= 0; i--){
			var tempRes = "";
			ost = 0;
			for (let j = max.length - 1; j >= 0; j--){
				let tempA = parseInt(min[i]);
				let tempB = parseInt(max[j]);
				let c = (tempA * tempB) + parseInt(ost);
				ost = 0;
				c = c.toString();
				if (c.length == 2){
					ost = c[0];
				}
				tempRes = c[c.length -1] + tempRes;
			}
			if (parseInt(ost) != 0)
				tempRes = ost + tempRes;

			tempRes += Math.pow(10,(min.length - 1) - i).toString().substring(1);
			res = LongInt.sum(res, tempRes);
		}

		if(negative)
			res = "-" + res;

		return res;
	}

	static sum(a, b){
		a = a.toString();
		b = b.toString();

		var negative = false
		if (a[0] == '-' && b[0] == '-'){
			a = a.substring(1);
			b = b.substring(1);
			negative = true;
		}

		var max = a, min = b;

		if (max.toString().length < min.toString().length){
			[max, min] = [min, max]; //swap
		}

		var maxInd = max.toString().length - 1, minInd = min.toString().length - 1, ost = 0;
		var res = "";

		for (let i = 0; i < max.toString().length; i++){
			var n1 = parseInt(max[maxInd])

			if (minInd !== -1){
				var n2 = parseInt(min[minInd])
				var c = n1 + n2 + ost
				ost = 0;

				if (c >= 10) {
					c -= 10;
					ost++
				}
				res += c
				minInd--;
			} else{
				var c = n1 + ost
				ost = 0
				if (c >= 10){
					c -= 10
					ost++
				}
				res += c
			}
			maxInd--;
		}

		res += ost;

		while(res[res.length - 1] == '0' && res.length !== 1){
			res = res.substring(0, res.length - 1);
		}

		res = res.split("").reverse().join(""); //разворачиваем строку и возвращаем число

		if(negative)
			res = "-" + res;

		return res;
	}

	static diff(a, b){
		var max = a.toString(), min = b.toString();
		var res = "";
		var negative = false;

		if (LongInt.greater(min, max)){
			[max, min] = [min, max]; //swap
			negative = true;
		}

		var maxInd = max.toString().length - 1, minInd = min.toString().length - 1, ost = 0;

		for (let i = 0; i < max.toString().length; i++){
			var n1 = parseInt(max[maxInd]);

			if (minInd !== -1){
				var n2 = parseInt(min[minInd])
				var c = n1 - n2 - ost;
				ost = 0;

				if (c < 0) {
					c += 10;
					ost++;
				}
				res += c;
				minInd--;
			} else{
				var c = n1 - ost;
				ost = 0;
				if (c < 0){
					c += 10
					ost++
				}
				res += c
			}
			maxInd--;
		}

		res += ost;

		while(res[res.length - 1] == '0' && res.length !== 1){
			res = res.substring(0, res.length - 1); //
		}

		res = res.split("").reverse().join("");

		if(negative)
			res = "-" + res;

		return res;
	}

	static div(a, b){
		a = a.toString();
		b = b.toString();

		var negative = false;
		if (LongInt.less(a, 0)){
			negative = !negative;
			a = LongInt.abs(a);
		}
		if (LongInt.less(b, 0)){
			negative = !negative;
			b = LongInt.abs(b);
		}

		if (LongInt.equals(a, "0") && !LongInt.equals(b, "0"))
			return "0";
		if (LongInt.equals(b, "0"))
			return "Division by zero";

		if (LongInt.less(a, b))
			return "0";

		var res = "", tempA = "", past = "", start = 0;;

		for (let i = 0; i < a.length; i++){
			tempA += a[i];
			if (LongInt.lessOrEquals(b, tempA)){
				start = i;
				break;
			}
		}

		if (tempA === a){
			for (let i = 1; i < 10; i++){
				if (LongInt.greater(LongInt.multiply(i, b), a)){
					res = (i - 1).toString();
					if (negative)
						res = "-" + res;
					return res;
				} else if (LongInt.equals(LongInt.multiply(i, b), a)){
					res = (i).toString();
					if (negative)
						res = "-" + res;
					return res;
				}

			}
		}

		for (let i = start; i < a.length; i++){
			for (let j = 1; j <= 9; j++){
				let tempRes = LongInt.multiply(j, b);
				if (LongInt.greater(tempRes, tempA)){
					res = res + (j - 1).toString();
					tempA = LongInt.diff(tempA, LongInt.multiply(b, (j - 1)));
					break;
				} else if (LongInt.equals(tempRes, tempA)){
					res = res + (j).toString();
					tempA = "";
					break;
				} else if (j == 9){
					res = res + "9";
					tempA = LongInt.diff(tempA, LongInt.multiply(b, j));
					break;
				}
			}
			tempA = tempA + a[i + 1];
			if (tempA == "00")
				tempA = "0";
		}
	
		if (negative)
			res = "-" + res;

		return res;
	}

	static mod(a, b){
		if (LongInt.equals(a, "0") && !LongInt.equals(b, "0"))
			return "0";
		if (LongInt.equals(b, "0"))
			return "Division by zero";

		if (LongInt.less(LongInt.abs(a), LongInt.abs(b)))
			return a.toString()

		a = LongInt.abs(a);
		b = LongInt.abs(b);

		return (LongInt.diff(a, (LongInt.multiply(b, LongInt.div(a, b)))));
	}

	static greater(a, b){
		a = a.toString();
		b = b.toString();

		if (a[0] == '-' && b[0] != '-')
			return false;
		if (a[0] != '-' && b[0] == '-')
			return true;

		if (a[0] == '-' && b[0] == '-'){
			a = a.substring(1);
			b = b.substring(1);
			return LongInt.less(a, b);
		}

		if (a.length > b.length)
			return true;
		else if (a.length < b.length)
			return false;

		for (let i = 0; i < a.length; i++){
			var n1 = parseInt(a[i]), n2 = parseInt(b[i]);
			if (n1 > n2){
				return true;
			}
			else if (n2 > n1){
				return false;
			}
		}

		return false;
	}

	static greaterOrEquals(a, b){
		return (LongInt.greater(a, b) || LongInt.equals(a, b));
	}

	static less(a, b){
		a = a.toString();
		b = b.toString();

		if (a[0] == '-' && b[0] != '-')
			return true;
		if (a[0] != '-' && b[0] == '-')
			return false;

		if (a[0] == '-' && b[0] == '-'){
			a = a.substring(1);
			b = b.substring(1);
			return LongInt.greater(a, b);
		}

		if (a.length > b.length)
			return false;
		else if (a.length < b.length)
			return true;

		for (let i = 0; i < a.length; i++){
			var n1 = parseInt(a[i]), n2 = parseInt(b[i]);
			if (n1 < n2)
				return true;
			else if (n2 < n1)
				return false;
		}

		return false;
	}

	static lessOrEquals(a, b){
		return (LongInt.less(a, b) || LongInt.equals(a, b));
	}

	static equals(a, b){
		a = a.toString();
		b = b.toString();

		if (a.length > b.length)
			return false;
		else if (a.length < b.length)
			return false;

		if ((a[0] == '-' && b[0] != '-' ) || (a[0] != '-' && b[0] == '-'))
			return false;

		if (a[0] == '-' && b[0] == '-'){
			a = a.substring(1);
			b = b.substring(1);
		}

		for (let i = 0; i < a.length; i++){
			var n1 = parseInt(a[i]), n2 = parseInt(b[i]);
			if (n1 != n2)
				return false;
		}

		return true;
	}

	static abs(a){
		a = a.toString();
		if (a[0] == '-') {
			return a.substring(1);
		}
		return a;
	}
}
