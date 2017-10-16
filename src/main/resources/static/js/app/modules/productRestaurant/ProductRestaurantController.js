'use strict';

angular.module('fdpApp').controller('ProductRestaurantController',
    ['ProductRestaurantService', '$scope','urls',  function(ProductRestaurantService, $scope, urls) {

        var self = this;
        self.productRestaurant = {};
        self.productRestaurants=[];

        self.submit = submit;
        self.getAllProductRestaurants = getAllProductRestaurants;
        self.getAllProducts = getAllProducts;
        self.createProductRestaurant = createProductRestaurant;
        self.updateProductRestaurant = updateProductRestaurant;
        self.removeProductRestaurant = removeProductRestaurant;
        self.editProductRestaurant = editProductRestaurant;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Submitting');
            if (self.productRestaurant.id === undefined || self.productRestaurant.id === null) {
                console.log('Saving New ProductRestaurant', self.productRestaurant);
                createProductRestaurant(self.productRestaurant);
            } else {
                updateProductRestaurant(self.productRestaurant, self.productRestaurant.id);
                console.log('ProductRestaurant updated with id ', self.productRestaurant.id);
            }
        }

        function createProductRestaurant(productProductRestaurant) {
            console.log('About to create productProductRestaurant');
            productProductRestaurant.restaurantId = urls.RESTAURANT_ID;
            ProductRestaurantService.createProductRestaurant(productProductRestaurant)
                .then(
                    function (response) {
                        console.log('ProductRestaurant created successfully');
                        self.successMessage = 'ProductRestaurant created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.productRestaurant={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating ProductRestaurant');
                        self.errorMessage = 'Error while creating ProductRestaurant: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }

        function updateProductRestaurant(productProductRestaurant, id){
            console.log('About to update productProductRestaurant');
            productProductRestaurant.restaurantId = urls.RESTAURANT_ID;
            ProductRestaurantService.updateProductRestaurant(productProductRestaurant, id)
                .then(
                    function (response){
                        console.log('ProductRestaurant updated successfully');
                        self.successMessage='ProductRestaurant updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating ProductRestaurant');
                        self.errorMessage='Error while updating ProductRestaurant '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeProductRestaurant(id){
            console.log('About to remove ProductRestaurant with id '+id);
            ProductRestaurantService.removeProductRestaurant(id)
                .then(
                    function(){
                        console.log('ProductRestaurant '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing productProductRestaurant '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllProductRestaurants(){
            return ProductRestaurantService.getAllProductRestaurants();
        }

        function getAllProducts(){
            return ProductRestaurantService.getAllProducts();
        }

        function editProductRestaurant(id) {
            self.successMessage='';
            self.errorMessage='';
            ProductRestaurantService.getProductRestaurant(id).then(
                function (productProductRestaurant) {
                    self.productRestaurant = productProductRestaurant;
                },
                function (errResponse) {
                    console.error('Error while removing productRestaurant ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.productRestaurant={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }


    ]);