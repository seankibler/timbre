(ns taoensso.timbre.appenders.syslog
  "Timbre appender to syslog. Requires https://github.com/joshrotenberg/brolog"
  {:author "Sean Kibler (@seankibler)"}
  (:require [taoensso.timbre :as timbre])
  (:use [brolog.core]))

(defn syslog-appender
  "Returns a syslog appender."
  [& [opts]]

  (let [{:keys [ident facility syslog-options]
         :or {:ident "timbre-syslog" :facility log-local7}} opts]
    {:enabled?   true
     :async?     false
     :min-level  nil
     :rate-limit nil
     :output-fn :inherit
     :fn
     (fn [data]
       (let [{:keys [instant level output-fn]} data
             output-str (output-fn data)]
         (log level output-str)))}))

(comment
  ;; Create a syslog appender with default options:
  (syslog-appender)

  ;; Create an example appender with default options, but override `:min-level`:
  (merge (syslog-appender) {:min-level :debug}))
