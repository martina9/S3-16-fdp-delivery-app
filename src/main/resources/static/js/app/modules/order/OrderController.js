'use strict';

angular.module('fdpApp').controller('OrderController',
    ['OrderService', '$scope', 'urls',  function( OrderService, $scope , urls) {

        var self = this;
        self.getAllProductRestaurantsLive = [];
        self.order = {};
        self.orders=[];
        self.ids ={};
        self.selectedRestaurant = {};
        self.userproductRestaurants = [];
        self.submit = submit;
        self.confirmOrder = confirmOrder;
        self.searchProductRestaurantLive = searchProductRestaurantLive;
        self.getAllRestaurants = getAllRestaurants;
        self.searchProductRestaurant = searchProductRestaurant;
        self.getAllUserOrders = getAllUserOrders;
        self.getAllProductRestaurants = getAllProductRestaurants;
        self.getAllRestaurantOrders = getAllRestaurantOrders;
        self.createOrder = createOrder;
        self.updateOrder = updateOrder;
        self.removeOrder = removeOrder;
        self.editOrder = editOrder;
        self.reset = reset;
        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function getAllRestaurants(){
            return OrderService.getAllRestaurants();
        }

        function confirmOrder(){
            console.log('selectedRestaurant',self.selectedRestaurant)
            console.log('ids ',self.ids);
            var list = self.getAllProductRestaurants();

            var products = [];
            var orderToConfirm = {
                deliveryType:self.order.deliveryType,
                address:self.order.address,
                city:self.order.city,
                phoneNumber:self.order.phoneNumber ,
                email :self.order.email,
                userId:urls.USER_ID,
                restaurantId:self.selectedRestaurant,
                confirmationDate:new Date(),
                products: new Array()
            };

            angular.forEach(list, function(value, key) {
                angular.forEach(self.ids, function (selfValue, selfKe) {
                    if (value.id == selfValue) {
                        var product = [{quantity:value.quantity},{productId:value.productId}];
                        orderToConfirm.products.push(product);
                    }
                });
            });

            self.createOrder(orderToConfirm);
            console.log('orderToConfirm',orderToConfirm);
            console.log('userproductRestaurants ',  self.userproductRestaurants);
        }

        function searchProductRestaurantLive(restaurantId) {
            console.log('userproductRestaurants request');
            if(restaurantId === undefined || restaurantId == null) {
                self.getAllProductRestaurantsLive = [];
                return;
            }
            OrderService.loadAllProductRestaurants(restaurantId).then(
                function (response) {
                    console.log('productRestaurants Loaded successfully');
                    self.successMessage = 'productRestaurants Loaded successfully';
                    self.errorMessage = '';
                    self.done = true;
                    self.getAllProductRestaurantsLive = response.data;
                },
                function (errResponse) {
                    console.error('Error while productRestaurants Loaded');
                    self.errorMessage = 'Error while productRestaurants Loaded: ' + errResponse.data.errorMessage;
                    self.successMessage='';
                }
            );
        }

        function searchProductRestaurant(restaurantId) {
            console.log('userproductRestaurants request');
            OrderService.loadAllProductRestaurants(restaurantId).then(
                function (response) {
                    console.log('productRestaurants Loaded successfully');
                    self.successMessage = 'productRestaurants Loaded successfully';
                    self.errorMessage = '';
                    self.done = true;
                },
                function (errResponse) {
                    console.error('Error while productRestaurants Loaded');
                    self.errorMessage = 'Error while productRestaurants Loaded: ' + errResponse.data.errorMessage;
                    self.successMessage='';
                }
            );
        }

        function submit() {

        }

        function createOrder(order) {
            console.log('About to create order');
            OrderService.createOrder(order)
                .then(
                    function (response) {
                        console.log('Order created successfully');
                        self.successMessage = 'Order created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.order={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating Order');
                        self.errorMessage = 'Error while creating Order: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }

        function updateOrder(order, id){
            console.log('About to update order');
            OrderService.updateOrder(order, id)
                .then(
                    function (response){
                        console.log('Order updated successfully');
                        self.successMessage='Order updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Order');
                        self.errorMessage='Error while updating Order '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }

        function removeOrder(id){
            console.log('About to remove Order with id '+id);
            OrderService.removeOrder(id)
                .then(
                    function(){
                        console.log('Order '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing order '+id +', Error :'+errResponse.data);
                    }
                );
        }

        function getAllProductRestaurants(){
            return OrderService.getAllProductRestaurants();
        }

        function getAllRestaurantOrders(){
            return OrderService.getAllRestaurantOrders();
        }

        function getAllUserOrders(){
            return OrderService.getAllUserOrders();
        }

        function editOrder(id) {
            self.successMessage='';
            self.errorMessage='';
            OrderService.getOrder(id).then(
                function (order) {
                    self.order = order;
                },
                function (errResponse) {
                    console.error('Error while removing order ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.order={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }


    ]);