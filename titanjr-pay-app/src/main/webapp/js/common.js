  function sub(a, b) {
        var c, d, e;
        try {
            c = a.toString().split(".")[1].length;
        } catch (f) {
            c = 0;
        }
        try {
            d = b.toString().split(".")[1].length;
        } catch (f) {
            d = 0;
        }
       
       return e = Math.pow(10, Math.max(c, d)), (mul(a, e) - mul(b, e)) / e;
    }
    
    function mul(a, b) {//改进的乘法
        var c = 0,
            d = a.toString(),
            e = b.toString();
        try {
            c += d.split(".")[1].length;
        } catch (f) {}
        try {
            c += e.split(".")[1].length;
        } catch (f) {}
        return Number(d.replace(".", "")) * Number(e.replace(".", "")) / Math.pow(10, c);
    }
    
    function accAdd(a, b) {  
        var c, d, e;  
        try {  
            c = a.toString().split(".")[1].length;  
        } catch (f) {  
            c = 0;  
        }  
        try {  
            d = b.toString().split(".")[1].length;  
        } catch (f) {  
            d = 0;  
        }  
        return e = Math.pow(10, Math.max(c, d)), (mul(a, e) + mul(b, e)) / e;  
    } 