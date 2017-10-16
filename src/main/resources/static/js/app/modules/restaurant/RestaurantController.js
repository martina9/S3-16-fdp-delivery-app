'use strict';

angular.module('fdpApp').controller('RestaurantController',
    ['RestaurantService', '$scope',  function( RestaurantService, $scope) {

        var self = this;
        self.restaurant = {};
        self.restaurants=[];

        self.submit = submit;
        self.getAllRestaurants = getAllRestaurants;
        self.getAllCategories = getAllCategories;
        self.createRestaurant = createRestaurant;
        self.updateRestaurant = updateRestaurant;
        self.removeRestaurant = removeRestaurant;
        self.editRestaurant = editRestaurant;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Submitting');
            if (self.restaurant.id === undefined || self.restaurant.id === null) {
                console.log('Saving New Restaurant', self.restaurant);
                createRestaurant(self.restaurant);
            } else {
                updateRestaurant(self.restaurant, self.restaurant.id);
                console.log('Restaurant updated with id ', self.restaurant.id);
            }
        }

        function createRestaurant(restaurant) {
            console.log('About to create restaurant');
            RestaurantService.createRestaurant(restaurant)
                .then(
                    function (response) {
                        console.log('Restaurant created successfully');
                        self.successMessage = 'Restaurant created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.restaurant={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating Restaurant');
                        self.errorMessage = 'Error while creating Restaurant: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateRestaurant(restaurant, id){
            console.log('About to update restaurant');
            RestaurantService.updateRestaurant(restaurant, id)
                .then(
                    function (response){
                        console.log('Restaurant updated successfully');
                        self.successMessage='Restaurant updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Restaurant');
                        self.errorMessage='Error while updating Restaurant '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeRestaurant(id){
            console.log('About to remove Restaurant with id '+id);
            RestaurantService.removeRestaurant(id)
                .then(
                    function(){
                        console.log('Restaurant '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing restaurant '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllRestaurants(){
            return RestaurantService.getAllRestaurants();
        }

        function getAllCategories(){
            return RestaurantService.getAllCategories();
        }

        function editRestaurant(id) {
            self.successMessage='';
            self.errorMessage='';
            RestaurantService.getRestaurant(id).then(
                function (restaurant) {
                    self.restaurant = restaurant;
                },
                function (errResponse) {
                    console.error('Error while removing restaurant ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.restaurant={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }


    ]);