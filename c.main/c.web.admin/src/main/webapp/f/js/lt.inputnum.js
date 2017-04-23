function accMul(arg1,arg2){
    var m=0,s1=arg1.toString(),s2=arg2.toString();
    try{m+=s1.split(".")[1].length}catch(e){}
    try{m+=s2.split(".")[1].length}catch(e){}
    return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)
} 
//闭包限定命名空间
(function ($) {
    $.fn.extend({
        "inputnum": function (options) {
            var opts = $.extend({}, defaluts, options); //使用jQuery.extend 覆盖插件默认参数 html += '';
            var that = this;
            var html='<div class="num">';
            html += '<a class="num_plus" flag="add"><i class="icon iconfont">&#xe626;</i></a>';
            html += '<input type="number" placeholder="" pattern="[0-9]\*" name="num" value="'+opts.defNum+'"  />';
            html += '<a style="border-left: 1px solid #ccc; border-right: 0px;" class="num_plus" flag="sub"><i class="icon iconfont">&#xe62e;</i></a>';
            html += '</div>';
            html += '<span class="fr mt5">合计：<em class="amount">&yen;'+accMul(opts.defNum,opts.defPrice)+'</em></span>';
            html += '';
            html += '';
            that.append(html);
            $(that).find(".num_plus[flag=add]").click(function(){
               var ele = $(this).next("input[name=num]");
     		   var val = ele.val();
     		   val = parseInt(val)+ 1;
     		   ele.val(val);
     		   $(this).parent().next(".fr").find(".amount").html("&yen;"+accMul(val,opts.defPrice));
     		   opts.changeCallback(that);
     	   });
            $(that).find(".num_plus[flag=sub]").click(function(){
                var ele = $(this).prev("input[name=num]");
      		   var val = ele.val();
      		   val = parseInt(val)- 1;
      		   if(val<1)val=1;
      		   ele.val(val);
      		   $(this).parent().next(".fr").find(".amount").html("&yen;"+accMul(val,opts.defPrice));
      		   opts.changeCallback(that);
      	   });
            $(that).find("input[name=num]").change(function(){
        	    var value =$(this).val();
	   			if( $.trim(value)==''){
	   				//请输入数量！
	   				value=1;
	   				$(this).val(value);
	   			}
	   			else if(parseInt($.trim(value)) < 0 ){
	   				//数量不能小于0！
	   				value=1;
	   				$(this).val(value);
	   			}
	   			if(parseInt($.trim(value)) > 100){
	   				//数量不能大于100
	   				value=1;
	   				$(this).val(value);
	   			}
	   			if(isNaN(value) ){
	   				//数量必须是数字
	   				value=1;
	   				$(this).val(value);
	   			}
	   			$(this).parent().next(".fr").find(".amount").html("&yen;"+accMul(value,opts.defPrice));
	   			opts.changeCallback(that);
           });
           
            return this;

        }
    });
    //默认参数
    var defaluts = {
    	defPrice: 0,
        defNum: 1,
        defSelectId:0,
        changeCallback:function(){}
    };
})(window.jQuery);
