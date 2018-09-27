/**
 * 
 */
$(function(){
	// 从URL里获取productId参数的值
	var productID = getQueryString('productID');
	// 通过productId获取商品信息的URL
	var infoUrl = '/o2o/shopadmin/getproductbyid?productID=' + productID;
	// 获取当前店铺设定的商品类别列表的URL
	var categoryUrl = '/o2o/shopadmin/getproductcategorylist';
	// 更新商品信息的URL
	var productPostUrl = '/o2o/shopadmin/modifyproduct';
	// 由于商品添加和编辑使用的是同一个页面，
	// 该标识符用来标明本次是添加还是编辑操作
	var isEdit = false;
	if (productID) {
		//更新
		// 若有productId则为编辑操作
		getInfo(productID);
		isEdit = true;
	} else {
		//添加
		getCategory();
		productPostUrl = '/o2o/shopadmin/addproduct';//url变更，调用添加controll
	}
	
	// 点击“编辑”获取需要编辑的商品的商品信息，并赋值给表单
	function getInfo(id) {
		$.getJSON(
					infoUrl,
					function(data) {
						if (data.success) {
							// 从返回的JSON当中获取product对象的信息，并赋值给表单
							var product = data.product;
							$('#product-name').val(product.productname);
							$('#product-desc').val(product.productdesc);
							$('#priority').val(product.level);
							$('#normal-price').val(product.normalprice);
							$('#promotion-price').val(
									product.promotionprice);
							// 获取原本的商品类别以及该店铺的所有商品类别列表
							var optionHtml = '';
							var optionArr = data.pcList;
							var optionSelected = product.productCategory.productCategoryid;
							// 生成前端的HTML商品类别列表，并默认选择编辑前的商品类别
							optionArr
									.map(function(item, index) {
										var isSelect = optionSelected === item.productCategoryid ? 'selected'
												: '';
										optionHtml += '<option data-value="'
												+ item.productCategoryid
												+ '"'
												+ isSelect
												+ '>'
												+ item.productCategoryname
												+ '</option>';
									});
							$('#category').html(optionHtml);
						}else{
							$.toast(data.msg);
						}
					});
	}
	
	// 加载该店铺的商品类别
	function getCategory() {
		$.getJSON(categoryUrl, function(data) {
			if (data.success) {
				var productCategoryList = data.data;
				var optionHtml = '';
				productCategoryList.map(function(item, index) {
					optionHtml += '<option data-value="'
							+ item.productCategoryid + '">'
							+ item.productCategoryname + '</option>';
				});
				$('#category').html(optionHtml);
			}
		});
	}
	
	// 针对商品详情图控件组，若该控件组的最后一个元素发生变化（即上传了图片），
	// 且控件总数未达到6个，则生成新的一个文件上传控件
	$('.detail-img-div').on('change', '.detail-img:last-child', function() {
		if ($('.detail-img').length < 6) {
			$('#detail-img').append('<input type="file" class="detail-img">');
		}
	});
	
	// 提交按钮的事件响应，分别对商品添加和编辑操作做不同响应
	$('#submit').click(
			function() {
				// 创建商品json对象，并从表单里面获取对应的属性值
				var product = {};
				product.productname = $('#product-name').val();
				product.productdesc = $('#product-desc').val();
				product.level = $('#priority').val();
				product.normalprice = $('#normal-price').val();
				product.promotionprice = $('#promotion-price').val();
				// 获取选定的商品类别值
				product.productCategory = {
						productCategoryid : $('#category').find('option').not(
							function() {
								return !this.selected;
							}).data('value')
				};
				product.productid = productID;

				// 获取缩略图文件流
				var thumbnail = $('#small-img')[0].files[0];
				// 生成表单对象，用于接收参数并传递给后台
				var formData = new FormData();
				formData.append('img', thumbnail);
				// 遍历商品详情图控件，获取里面的文件流
				$('.detail-img').map(
						function(index, item) {
							// 判断该控件是否已选择了文件
							if ($('.detail-img')[index].files.length > 0) {
								// 将第i个文件流赋值给key为productImgi的表单键值对里
								formData.append('detailImg' + index,
										$('.detail-img')[index].files[0]);
							}
						});
				// 将product json对象转成字符流保存至表单对象key为productStr的的键值对里
				formData.append('productStr', JSON.stringify(product));
				// 获取表单里输入的验证码
				var kaptcha = $("#j_captcha").val();
				if (!kaptcha) {
					$.toast('请输入验证码！');
					return;
				}
				formData.append('kaptcha', kaptcha);
				// 将数据提交至后台处理相关操作
				$.ajax({
					url : productPostUrl,
					type : 'POST',
					data : formData,
					contentType : false,
					processData : false,
					cache : false,
					success : function(data) {
						if (data.success) {
							$.toast('提交成功！');
							$('#captcha_img').click();
						} else {
							$.toast('提交失败！');
							$('#captcha_img').click();
						}
					}
				});
			});
})