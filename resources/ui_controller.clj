;; Make this script can call sshgc.controller functions directly
(use 'sshgc.controller)

;; Since we need to modify collection list, import javafx.collections.FXCollections
(import '[javafx.collections FXCollections])

;;;; Initialize UI

;; setup window_size FXCollections
(let [res-list '("1280x1024" "800x600")]
(doto window_size
  (.setItems (FXCollections/observableList
              ;; if user has Xephyr, add fullscreen option
              (if xephyr-exist?
                (conj res-list "fullscreen") res-list)))

  (-> (.getSelectionModel) (.select "fullscreen"))))

;; setup session FXCollections
(doto session
  (.setItems (FXCollections/observableList
              '("1" "2" "3")))
  (-> (.getSelectionModel) (.select "1")))

;; init ui for test
(.setText account "yenchin")
(.setText ip "192.168.1.172")

;;;; Actions
(defn get-item
  "Simple wrapper to get Choicebox selected item."
  [x]
  (-> x (.getSelectionModel) (.getSelectedItem)))

(defn action
  "Create display and run ssh commands."
  []
  (create-display (get-item window_size) (get-item session))
  (ssh-connect (.getText account) (.getText ip) (.getText password) (get-item session)))
