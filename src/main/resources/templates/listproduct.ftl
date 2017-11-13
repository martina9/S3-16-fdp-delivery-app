<!DOCTYPE html>
<html lang="en">
	<head>
		<link href="css/bootstrap-min.css" rel="stylesheet"/>
	</head>
	<body>
		<div class="generic-container" style="margin-left: 100px;">
			<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading"><span class="lead">Product </span></div>
				<div class="panel-body">
					<div class="form-container">
						<div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
						<div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
						<form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
							<input type="hidden" ng-model="ctrl.product.id" />
							<div class="row">
								<div class="form-group col-md-12">
									<label class="col-md-2 control-lable" for="uname">Name</label>
									<div class="col-md-7">
										<input type="text" ng-model="ctrl.product.name" id="uname" class="username form-control input-sm" placeholder="Enter product name" required ng-minlength="3"/>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="form-group col-md-12">
									<label class="col-md-2 control-lable" for="code">Code</label>
									<div class="col-md-7">
										<input type="text" ng-model="ctrl.product.code" id="code" class="form-control input-sm" placeholder="Enter the product Code." required ng-minlength="3"/>
									</div>
								</div>
							</div>
							<div class="row">
                                <div class="form-group col-md-12">
                                    <label class="col-md-2 control-lable" for="category">Category</label>
                                    <div class="form-group">
                                        <i class="fa fa-key"></i>
                                        <select id="category" class="form-control" ng-model="ctrl.product.categoryId" ng-options="category.id as category.name for category in ctrl.getAllCategories() track by category.id">
                                            <option value=""><--Select category--></option>
                                        </select>
                                    </div>
								</div>
                            </div>

								<#--<div class="form-group col-md-12">-->
									<#--<label class="col-md-2 control-lable" for="category">Category</label>-->
									<#--<div class="col-md-7">-->
										<#--<select size="6" name="category" class="dropdown" ng-model="ctrl.product.categoryId" ng-options="category.id as category.name for category in ctrl.getAllCategories() track by category.id">-->
											<#--<option value="">--Select Category--</option>-->
											<#--<!-- <option ng-repeat="option in ctrl.getAllCategories()" ng-value="option.id">{{option.name}}</option> &ndash;&gt;-->
										<#--</select>-->
									<#--</div>-->
								<#--</div>-->

							<div class="row">
								<div class="form-actions floatRight">
									<input type="submit"  value="{{!ctrl.product.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine">
									<button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading"><span class="lead">List of Products </span></div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-hover">
							<thead>
							<tr>
								<th>ID</th>
								<th>NAME</th>
								<th>CODE</th>
								<th>CATEGORY</th>
								<th width="100"></th>
								<th width="100"></th>
							</tr>
							</thead>
							<tbody>
							<tr ng-repeat="p in ctrl.getAllProducts()">
								<td>{{p.id}}</td>
								<td>{{p.name}}</td>
								<td>{{p.code}}</td>
								<td>{{p.categoryName}}</td>
								<td><button type="button" ng-click="ctrl.editProduct(p.id)" class="btn btn-success custom-width">Edit</button></td>
								<td><button type="button" ng-click="ctrl.removeProduct(p.id)" class="btn btn-danger custom-width">Remove</button></td>
							</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
        <script src="js/lib/bootstrap-min.js" ></script>
	</body>
</html>

