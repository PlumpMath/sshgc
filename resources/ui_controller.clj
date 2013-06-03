;; Make this script can call sshgc.controller functions directly
(use 'sshgc.controller)

;; We use sshgc.config to handle config file
(require '[sshgc.config :as conf])

;; Since we need to modify collection list, import javafx.collections.FXCollections
(import '[javafx.collections FXCollections])

;;;; Config

;; default config
(def default-config {:remember true :window-size "800x600" :display "1" :account "" :ip ""})

;; if config exist, use user config, else use default config
(def config (let [c (conf/read-config)]
              (if (nil? c) default-config c)))

;;;; Initialize UI

;; setup window_size FXCollections
(let [res-list '("1280x1024" "800x600")]
(doto window_size
  (.setItems (FXCollections/observableList
              ;; if user has Xephyr, add fullscreen option
              (if xephyr-exist?
                (conj res-list "fullscreen") res-list)))

  (-> (.getSelectionModel) (.select (:window-size config)))))

;; setup session FXCollections
(doto session
  (.setItems (FXCollections/observableList
              '("1" "2" "3")))
  (-> (.getSelectionModel) (.select (:display config))))

;; init ui for test
(.setText account (:account config))
(.setText ip (:ip config))
(.setSelected remember (:remember config))

;;;; Actions
(defn get-item
  "Simple wrapper to get Choicebox selected item."
  [x]
  (-> x (.getSelectionModel) (.getSelectedItem)))

(defn action
  "Create display and run ssh commands."
  []
  (let [remember? (.isSelected remember)
        window-size (get-item window_size)
        display (get-item session)
        account (.getText account)
        ip (.getText ip)
        password (.getText password)]
    (if remember?
      (conf/save-config {:remember remember? :window-size window-size
                         :display display :account account :ip ip}))
    (create-display window-size display)
    (ssh-connect account ip password display)))
