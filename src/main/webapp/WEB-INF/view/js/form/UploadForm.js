/*jslint browser: true, devel: true, indent: 4, nomen:true, vars: true */
/*global define */

define(function (require, exports, module) {
    "use strict";

    var BaseForm = require('./BaseForm');

    var UploadForm = BaseForm.extend({
        events: {
            'click .btn-submit': 'onSubmit'
        },

        /* ---------- Event Listener ---------- */

        onSubmit: function (evt) {
            evt.preventDefault();
            alert('submit');
        }
    });

    return UploadForm;
});

