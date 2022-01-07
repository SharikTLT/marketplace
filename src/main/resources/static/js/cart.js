var cart = {
  HIDE_CLASS: 'visually-hidden',
  URL_CART_SET: '/rest/cart/set',
  URL_CART_REMOVE: '/rest/cart/remove',

  /*
  * Инициализируем исходные значения и подключаем слушатели
  */
  init: function(){
    this.badge = $('#cart-totals')
    var $this = this;

    $('.cart-item').each(function(i, container){
      container = $(container);
      var itemId = container.data('id');
      container.find('.cart-add').click(function(el){ $this.addCart(itemId);});
      container.find('.cart-inc').click(function(el){ $this.incCart(itemId);});
      container.find('.cart-dec').click(function(el){ $this.decCart(itemId);});
    });

    this.getInfo();
  },

  /*
  * Время последнего обновления корзины
  */
  lastTsUpdate: new Date(),

  /*
  * ключ-значение для быстрого доступа к элементу корзины по productId
  */
  cartMap: {},

  /*
  * Обработка ответа, содержащего полного состояния корзины
  * Обновляем состояние, если получили свежий ответ. Исключаем обработку устаревших ответов
  * Формируем ключ-значение для дальнейшей работы с кнопками на странице
  * Запускаем обработку виджетов корзины у товаров на странице
  */
  handleCartResp: function(cart, ts){
    if(ts < this.lastTsUpdate){
      return;
    }
    this.lastTsUpdate = ts;
    this.lastCart = cart;
    console.log("CartResp: ", cart);
    this.setBadge(cart.length);
    this.cartMap = {}
    for(var item of cart){
      this.cartMap[item.product.id.toString()] = item;
    }
    this.updateCartHandlers();
  },

  /*
  * обновляем кнопки корзины у товаров на странице
  */
  updateCartHandlers: function(){
    var $this = this;
    $('.cart-item').each(function(i, el){
      el = $(el);
      var itemId = el.data('id');
      var item = $this.cartMap[itemId]
      if(item != undefined){
        el.find('.cart-added').removeClass($this.HIDE_CLASS);
        el.find('.cart-showed').addClass($this.HIDE_CLASS);
        el.find('.cart-count').html(item.count+" шт.")
      }else{
        el.find('.cart-added').addClass($this.HIDE_CLASS);
        el.find('.cart-showed').removeClass($this.HIDE_CLASS);
        console.log("hide item")
      }
    })
  },

  /*
  * Установка бейджа с количеством товаров в корзине
  */
  setBadge: function(count) {
    count = Number.parseInt(count)
    var text = count < 100 ? count : '99+';
    if(count > 0){
      this.badge.removeClass(this.HIDE_CLASS).html(text)
    }else{
      this.badge.addClass(this.HIDE_CLASS).html(text);
    }
  },

  /*
  * первоначальная загрузка информации о корзине
  */
  getInfo: function(){
    var $this = this;
    var ts = new Date()
    $.ajax({
      url: '/rest/cart/',
      success: function(res){
        $this.handleCartResp(res, ts);
      },
      error: function(xhr, status, err){
        $this.setBadge(0);
      }
    });
  },

  /*
  * добавление товара в корзину
  */
  addCart: function(itemId) {
    this.setCart(itemId, 1);
  },

  /*
  * увеличение количества товара в корзине
  */
  incCart: function(itemId) {
    var item = this.cartMap[itemId];
    if(item == undefined){
      console.error("incCart invalid id: ", itemId);
      return;
    }
    this.setCart(itemId, item.count + 1);
  },

  /*
  * уменьшение количества товара в корзине, при count = 1 происходит удаление товара из корзины
  */
  decCart: function(itemId) {
    var item = this.cartMap[itemId];
    if(item == undefined){
      console.error("incCart invalid id: ", itemId);
      return;
    }
    if(item.count > 1){
      this.setCart(itemId, item.count - 1);
    }else{
      this.removeCart(itemId);
    }
  },

  /*
  * вызов рест метода по установке товара в корзину с количеством
  */
  setCart: function(itemId, count){
    this.sendCartRequest(this.URL_CART_SET, { productId: itemId, count: count });
  },

  /*
  * вызов рест метода по удалению товара из корзины
  */
  removeCart: function(itemId){
      this.sendCartRequest(this.URL_CART_REMOVE, { productId: itemId });
  },

  /*
  * обертка для вызова ajax выбирает GET/POST при наличии аргумента data
  */
  sendCartRequest: function(url, data){
      var $this = this;
      var params = {
        url: url,
        type: 'GET',
        dataType: "json",
        success: function(res) {
          $this.handleCartResp(res);
        },
        error: function(xhr, status, err){
          console.error(status, err, xhr);
          alert(err);
        }
      }

      if(data !== null){
        params.type = 'POST';
        params.contentType = 'application/json; charset=utf-8';
        params.data = JSON.stringify(data);
      }

      $.ajax(params);
  }

}

/*
* после полной загрузки и готовности страницы запускаем инициализацию
*/
$(document).ready(function(){
    cart.init();
});