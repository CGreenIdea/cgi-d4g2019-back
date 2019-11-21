// Import required JS
import './js/index';

// Import stylesheet
import './css/main.scss';

//import {Constants} from './js/constant.mjs';
import {callRest} from './js/services.mjs';
window.callRest = callRest;

import {submitLogin} from './js/login.mjs';
window.submitLogin = submitLogin;

import {slideSubscriptionNext} from './js/inscription.mjs';
window.slideSubscriptionNext = slideSubscriptionNext;

import {slideSubscriptionPrevious} from './js/inscription.mjs';
window.slideSubscriptionPrevious = slideSubscriptionPrevious;

import {checkChangeToOwner} from './js/inscription.mjs';
window.checkChangeToOwner = checkChangeToOwner;

import {submitSubscriptionForm} from './js/inscription.mjs';
window.submitSubscriptionForm = submitSubscriptionForm;

import {checkDisclaimer} from './js/inscription.mjs';
window.checkDisclaimer = checkDisclaimer;
import {generateSVG} from './js/documents.mjs';
window.generateSVG = generateSVG;
