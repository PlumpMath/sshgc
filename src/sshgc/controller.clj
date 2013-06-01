(ns sshgc.controller
  (:gen-class)
  (:import [javafx.fxml FXML]
           [javafx.scene Scene]
           [javafx.event ActionEvent]
           [javafx.scene.text Text])
  (:require [sshgc.dialog :as dialog]))

(defn dependancy-missing []
  (dialog/error "Dependance Missing" "Please install Xnet or Xeyphr")
  )


;; (defn create-display []
;;   (let ((app "Xnest"))
;;   )
