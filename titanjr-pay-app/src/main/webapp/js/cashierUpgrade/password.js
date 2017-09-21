var Methods={
             $:function (arg, context) {
                var tagAll, n, eles = [], i, sub = arg.substring(1);
                context = context || document;
                if (typeof arg == 'string') {
                    switch (arg.charAt(0)) {
                        case '#':
                            return document.getElementById(sub);
                            break;
                        case '.':
                            if (context.getElementsByClassName) return context.getElementsByClassName(sub);
                            tagAll = Methods.$('*', context);
                            n = tagAll.length;
                            for (i = 0; i < n; i++) {
                                if (tagAll[i].className.indexOf(sub) > -1) eles.push(tagAll[i]);
                            }
                            return eles;
                            break;
                        default:
                            return context.getElementsByTagName(arg);
                            break;
                    }
                }
            },
            on:function(obj,type,handle){
                if(obj){
                    obj.addEventListener ? obj.addEventListener(type, handle, false) : obj.attachEvent('on' + type, handle);
                }
            },
            getClassNames:function(classStr,tagName){  
              if (document.getElementsByClassName) {  
                    return document.getElementsByClassName(classStr)  
              }else {  
                    var nodes = document.getElementsByTagName(tagName),ret = [];           
                    for(i = 0; i < nodes.length; i++) {  
                         if(hasClass(classStr,nodes[i])){  
                                ret.push(nodes[i])  
                         }  
                    }  
                    return ret;  
               }  
            },
            getTarget:function(event){
                return event.target || event.srcElement;
            },
            stopPropagation:function (event) {
                event = event || window.event;
                event.stopPropagation ? event.stopPropagation() : event.cancelBubble = true;
            },
            /* �����ʽ�� */
            addClass:function (c, node) {
                if(!node)return;
                node.className = this.hasClass(c,node) ? node.className : node.className + ' ' + c ;
                node.className=this.trim(node.className)
            },

            /* �Ƴ���ʽ�� */
            removeClass:function (c, node) {
                var reg = new RegExp("(^|\\s+)" + c + "(\\s+|$)", "g");
                if(!this.hasClass(c,node))return;
                node.className = reg.test(node.className) ? node.className.replace(reg, '') : node.className;
            },

            /* �Ƿ���CLASS */
            hasClass:function (c, node) {
                if(!node || !node.className || node.className=="")return false;
                return node.className.indexOf(c)>-1;
            },
            /* ȥ�����˿ո� */
            trim:function (str) {
                return str.replace(/^\s+|\s+$/g,'');
            },
            getDefaultStyle:function(obj,attribute){  
             return obj.currentStyle?obj.currentStyle[attribute]:document.defaultView.getComputedStyle(obj,false)[attribute];   
            },
            _stopIt:function(e){  
                var targetN=this.getTarget(e);
                if(targetN.nodeName=="INPUT"||targetN.nodeName=="TEXTAREA") return;
                if(e.returnValue){  
                    e.returnValue = false ;  
                }  
                if(e.preventDefault ){  
                    e.preventDefault();  
                }                          
                return false;  
            }  
        } 
    if (!Array.prototype.indexOf)
        {
          Array.prototype.indexOf = function(elt /*, from*/)
          {
            var len = this.length >>> 0;
            var from = Number(arguments[1]) || 0;
            from = (from < 0)
                 ? Math.ceil(from)
                 : Math.floor(from);
            if (from < 0)
              from += len;
            for (; from < len; from++)
            {
              if (from in this &&
                  this[from] === elt)
                return from;
            }
            return -1;
          };
    }     
    function sixDigitPassword(args){             
        this.initialize.apply(this, arguments);      
    }
    sixDigitPassword.prototype={
        initialize:function(args){
            this.activeIdex=0;  
            this.str=[];          
            this.box=Methods.$("#"+args);
            this.inps=[];
            this.span=[];
            this.width=null;      
            this.clickFocus(this.box); 
            if(document!=window.parent.document) this.keyPress1();;                   
            Methods.getClassNames("sixDigitPassword","div")[0].click();
            this.keyPress();           
        },
        clickFocus:function(args){   
            var that=this;  
            Methods.on(args,"click",function(e){  
                that.box=this;
                if(Methods.getClassNames("active","i").length!=0){
                    var class_active=Methods.getClassNames("active","i")[0]
                    Methods.removeClass("active",class_active);   
                    var class_active_p=class_active.parentNode
                    class_active_p.getElementsByTagName("span")[0].style.visibility="hidden";             
                };
               
                var divList=Methods.getClassNames("sixDigitPassword","div");
                for(var i=0;i<divList.length;i++){
                    if(divList[i].getAttribute("f")){
                        divList[i].removeAttribute("f")
                        divList[i].getElementsByTagName("span")[0].style.visibility="hidden";
                    }
                }
                this.setAttribute("f","true");               
                that.inps=Methods.$("i",this); 
                that.width=parseInt(Methods.getDefaultStyle(that.inps[0],"width"))+1;
                that.span=Methods.$("span",this)[0];                                     
                Methods.addClass("active",that.inps[that.activeIdex]);  
                that.span.style.visibility="inherit"; 
                that.span.style.left=(that.width)*(that.activeIdex);
                Methods.stopPropagation(e);                                           
            });
            Methods.on(document,"click",function(e){
                var divList=Methods.getClassNames("sixDigitPassword","div");
                for(var i=0;i<divList.length;i++){
                    if(divList[i].getAttribute("f")){
                        divList[i].removeAttribute("f")
                    }
                }              
                if(that.inps.length!=""){
                    that.span.style.visibility="hidden"; 
                    var target=Methods.getTarget(e);
                    var num=0;
                    while(num<that.inps.length){
                       Methods.removeClass("active",that.inps[num]);
                       num++; 
                    }   
                }               
                //console.log(that.inps);                            
            });                           
        },
        keyPress:function(){            
            var that=this;    
            Methods.on(document,"keypress",function(e){
                var e=e||window.event;
                if(e.keyCode==13||e.keyCode==8) return false;
                var num=0;
                var value=false;
                while(num<that.inps.length){
                    if(Methods.hasClass("active",that.inps[num])){
                        value=true;
                    }              
                   num++; 
                }
                if(value==true){
                    var k=String.fromCharCode(e.charCode);               
                    if(that.activeIdex<that.inps.length){
                        that.str.push(k)                   
                        that.activeIdex++;
                        that.active();
                    }                   
                }              
            });   
            Methods.on(document,"keydown",function(e){                                
                var e=e||window.event;                
                if(e.keyCode==8){
                    var num=0;
                    var value=false;
                    while(num<that.inps.length){
                        if(Methods.hasClass("active",that.inps[num])){
                            value=true;
                        }              
                       num++; 
                    }
                    if(value==true||that.box.getAttribute("f")){                                               
                        if(that.activeIdex>0){               
                            that.str.pop();
                            that.activeIdex--;
                            that.activeD();                                             
                        }
                        return Methods._stopIt(e);  
                    } 
                }else if(e.keyCode==9){

                   return Methods._stopIt(e);  
                }           
            });            
        },
        keyPress1:function(){            
            var that=this;    
            Methods.on(window.parent.document,"keypress",function(e){
                var e=e||window.event;
                if(e.keyCode==13||e.keyCode==8) return false;
                var num=0;
                var value=false;
                while(num<that.inps.length){
                    if(Methods.hasClass("active",that.inps[num])){
                        value=true;
                    }              
                   num++; 
                }
                if(value==true){
                    var k=String.fromCharCode(e.charCode);               
                    if(that.activeIdex<that.inps.length){
                        that.str.push(k)                   
                        that.activeIdex++;
                        that.active();
                    }                   
                }              
            });   
            Methods.on(window.parent.document,"keydown",function(e){                                
                var e=e||window.event;                
                if(e.keyCode==8){
                    var num=0;
                    var value=false;
                    while(num<that.inps.length){
                        if(Methods.hasClass("active",that.inps[num])){
                            value=true;
                        }              
                       num++; 
                    }
                    if(value==true||that.box.getAttribute("f")){                                               
                        if(that.activeIdex>0){               
                            that.str.pop();
                            that.activeIdex--;
                            that.activeD();                                             
                        }
                        return Methods._stopIt(e);  
                    } 
                }else if(e.keyCode==9){

                   return Methods._stopIt(e);  
                }           
            });            
        },
        active:function(){
            var num=0;
            var x=this.activeIdex;           
            while(num<this.inps.length){
               Methods.removeClass("active",this.inps[num]);
               num++; 
            }
            Methods.addClass("active",this.inps[x]);
            this.inps[x-1].childNodes[0].style.visibility="inherit";
            if(this.activeIdex==this.inps.length){
                 this.span.style.left=this.width*(this.activeIdex-1)+"px";
            }else{
                 this.span.style.left=this.width*this.activeIdex+"px";
            }
            
        },
        activeD:function(){
            var num=0;
            var x=this.activeIdex           
            while(num<this.inps.length){
               Methods.removeClass("active",this.inps[num]);
               num++; 
            };
            Methods.addClass("active",this.inps[x]);
            this.inps[x].childNodes[0].style.visibility="hidden";
            this.span.style.left=this.width*this.activeIdex+"px";
        },
        returnStr:function(){
            return this.str.join(""); 
        }
    }      