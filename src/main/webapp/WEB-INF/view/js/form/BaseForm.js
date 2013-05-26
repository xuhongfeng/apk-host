/*jslint browser: true, devel: true, indent: 4, nomen:true, vars: true */
/*global define */

define(function (require, exports, module) {
    "use strict";

    var Backbone = require('../lib/backbone');
    var BaseView = require('../BaseView');

    var BaseForm = BaseView.extend();

    return BaseForm;
});

