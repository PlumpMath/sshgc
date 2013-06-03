(ns sshgc.config
  (:require [clojure.java.io :as io]))

(def config-file (str (System/getProperty "user.home") "/.config/sshgc.clj"))

(defn save-config
  "Save conf to config-file."
  [conf]
  (spit config-file conf))

(defn read-config
  "Read config file, if not exist return nil."
  []
  (if (.exists (io/as-file config-file))
    (read-string (slurp config-file))
    nil))
