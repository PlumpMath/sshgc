(use 'sshgc.controller)
(import '[javafx.collections FXCollections])

;;;; Initialize UI

;; setup window_size FXCollections
(doto window_size
  (.setItems (FXCollections/observableList
              '("Fullscreen"
                "1280x1024"
                "800x600"
                )))
  (-> (.getSelectionModel) (.select "Fullscreen")))

;; setup session FXCollections
(doto session
  (.setItems (FXCollections/observableList
              '("1" "2" "3")))
  (-> (.getSelectionModel) (.select "1")))


;;;; Actions
(defn action []
;;  (dependancy-missing)
  )
