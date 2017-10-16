'use strict';

angular.module('fdpApp').controller('OrderController',
    ['OrderService', '$scope',  function( OrderService, $scope) {

        var self = this;
        self.order = {};
        self.orders=[];
        self.ids ={};
        self.selectedRestaurant = {};
        self.userproductRestaurants = [];
        self.submit = submit;
        self.confirmOrder = confirmOrder;
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

            angular.forEach(self.getAllProductRestaurants(), function(value, key) {

                var order = {firstName:"John", lastName:"Doe", age:50, eyeColor:"blue"};
                angular.forEach(self.ids, function (selfValue, selfKe) {
                    if (value.id == selfValue)
                        self.userproductRestaurants.push(value);

                });
                console.log('userproductRestaurants ',  self.userproductRestaurants);

            });
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
            //return OrderService.getAllProductRestaurants();
        }

        function submit() {
            console.log('Submitting Searching ProductRestaurant' +self.selectedRestaurant);
            if (self.selectedRestaurant === undefined || self.selectedRestaurant  === null) {
                console.log('you have to choose restaurant First' + self.selectedRestaurant);
            } else {
                searchRestaurant(self.selectedRestaurant)
                console.log('selectedRestaurant updated' + self.selectedRestaurant);
            }
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